package com.tounans.easyiot.easyiotauth.controller;

import com.tounans.easyiot.common.client.UserClient;
import com.tounans.easyiot.common.entity.user.User;
import com.tounans.easyiot.common.exception.ExceptionCast;
import com.tounans.easyiot.common.model.auth.AuthCode;
import com.tounans.easyiot.common.model.auth.AuthToken;
import com.tounans.easyiot.common.model.auth.LoginRequest;
import com.tounans.easyiot.common.model.response.CommonCode;
import com.tounans.easyiot.common.model.response.ResponseResult;
import com.tounans.easyiot.common.utlis.CookieUtil;
import com.tounans.easyiot.easyiotauth.service.IAuthService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@RestController
@RequestMapping("/")
public class AuthController {

    @Value("${auth.clientId}")
    String clientId;
    @Value("${auth.clientSecret}")
    String clientSecret;
    @Value("${auth.cookieDomain}")
    String cookieDomain;
    @Value("${auth.cookieMaxAge}")
    int cookieMaxAge;

    @Autowired
    IAuthService authService;

    @Autowired
    UserClient userClient;

    @RequestMapping("/userlogin")
    public ResponseResult login(LoginRequest loginRequest) {
        if(loginRequest == null || StringUtils.isEmpty(loginRequest.getUsername())){
            ExceptionCast.cast(AuthCode.AUTH_USERNAME_NONE);
        }
        if(loginRequest == null || StringUtils.isEmpty(loginRequest.getPassword())){
            ExceptionCast.cast(AuthCode.AUTH_PASSWORD_NONE);
        }
        //账号
        String username = loginRequest.getUsername();
        //密码
        String password = loginRequest.getPassword();

        //申请令牌
        AuthToken authToken =  authService.login(username,password,clientId,clientSecret);

        //用户身份令牌
        String access_token = authToken.getAccess_token();
        //将令牌存储到cookie
        this.saveCookie(access_token);

        return new ResponseResult().success(access_token);
    }


    @RequestMapping("/userjwt")
    public ResponseResult userjwt() {
        //取出cookie中的用户身份令牌
        String uid = getTokenFormCookie();
        if(uid == null){
            return new ResponseResult().failure(CommonCode.FAIL);
        }

        //拿身份令牌从redis中查询jwt令牌
        AuthToken userToken = authService.getUserToken(uid);
        if(userToken!=null){
            //将jwt令牌返回给用户
            String jwt_token = userToken.getJwt_token();
            return new ResponseResult().success(jwt_token);
        }
        return null;
    }

        //退出
    @RequestMapping("/userlogout")
    public ResponseResult logout() {
        //取出cookie中的用户身份令牌
        String uid = getTokenFormCookie();
        //删除redis中的token
        boolean result = authService.delToken(uid);
        //清除cookie
        this.clearCookie(uid);
        return new ResponseResult().success(CommonCode.SUCCESS);
    }

    @RequestMapping("/registered")
    public ResponseResult registered(User user){
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        Boolean registered = userClient.AuthRegistered(user);
        if (registered){
            return new ResponseResult().success(CommonCode.SUCCESS);
        }
        return new ResponseResult().failure("用户名可能被占用咯~");
    }




    //将令牌存储到cookie
    private void saveCookie(String token){

        HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
        //HttpServletResponse response,String domain,String path, String name, String value, int maxAge,boolean httpOnly
        CookieUtil.addCookie(response,cookieDomain,"/","uid",token,cookieMaxAge,false);

    }
    //从cookie删除token
    private void clearCookie(String token){

        HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
        //HttpServletResponse response,String domain,String path, String name, String value, int maxAge,boolean httpOnly
        CookieUtil.addCookie(response,cookieDomain,"/","uid",token,0,false);

    }


    //取出cookie中的身份令牌
    private String getTokenFormCookie(){
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        if (!StringUtils.isEmpty(request.getHeader("uid"))){
            return request.getHeader("uid");
        }
        Map<String, String> map = CookieUtil.readCookie(request, "uid");
        if(map!=null && map.get("uid")!=null){
            String uid = map.get("uid");
            return uid;
        }
        return null;
    }
}
