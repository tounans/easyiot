package com.tounans.easyiot.common.utlis;
import com.alibaba.fastjson.JSONObject;
import com.tounans.easyiot.common.entity.user.User;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.security.jwt.Jwt;
import org.springframework.security.jwt.JwtHelper;
import org.springframework.security.jwt.crypto.sign.RsaVerifier;

import java.io.*;


public class JwtUtil {

     public static Jwt getJwt(String publicKeyFile,String jwtString){
          Resource resource = new ClassPathResource(publicKeyFile);
          File file = null;
          try {
               file = resource.getFile();
          } catch (IOException e) {
               e.printStackTrace();
               return null;
          }
          String publicKey = getTemplateContent(file);
          return JwtHelper.decodeAndVerify(jwtString, new RsaVerifier(publicKey));
     }

     public static String getToken(Jwt jwt){
          String claims = jwt.getClaims();
          JSONObject jsonObject = JSONObject.parseObject(claims);
          return jsonObject.getString("jti");
     }

     public static User getUser(Jwt jwt){
          String claims = jwt.getClaims();
          JSONObject jsonObject = JSONObject.parseObject(claims);
          String userName = jsonObject.getString("user_name");
          Integer id = jsonObject.getInteger("id");
          return new User().setId(id).setUsername(userName);
     }

     private static String getTemplateContent(File file){
          String msg = null;
          try {
               BufferedReader reader = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
               msg = reader.readLine();
          } catch (IOException e) {
               e.printStackTrace();
          }

          return msg;


     }
}
