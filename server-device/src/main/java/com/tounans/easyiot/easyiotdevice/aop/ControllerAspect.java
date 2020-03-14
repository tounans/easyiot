package com.tounans.easyiot.easyiotdevice.aop;

import com.tounans.easyiot.common.client.UserClient;
import com.tounans.easyiot.common.entity.user.User;
import com.tounans.easyiot.common.model.response.ResponseResult;
import org.apache.commons.lang.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Modifier;
import java.util.Enumeration;
import java.util.List;

@Aspect
@Component
public class ControllerAspect {



    @Autowired
    UserClient userClient;

    /**
     * 定义切入点，切入点为com.example.demo.aop.AopController中的所有函数
     *通过@Pointcut注解声明频繁使用的切点表达式
     */
    @Pointcut("execution(public * com.tounans.easyiot.*.controller.*.*(..)))")
    public void BrokerAspect(){
    }

    /**
     * 前置方法,在目标方法执行前执行
     * @param joinPoint 封装了代理方法信息的对象,若用不到则可以忽略不写
     */
//    @Before("BrokerAspect()")
    public void beforeMethod(JoinPoint joinPoint){
        System.out.println("目标方法名为:" + joinPoint.getSignature().getName());
        System.out.println("目标方法所属类的简单类名:" +        joinPoint.getSignature().getDeclaringType().getSimpleName());
        System.out.println("目标方法所属类的类名:" + joinPoint.getSignature().getDeclaringTypeName());
        System.out.println("目标方法声明类型:" + Modifier.toString(joinPoint.getSignature().getModifiers()));
        //获取传入目标方法的参数
        Object[] args = joinPoint.getArgs();
        for (int i = 0; i < args.length; i++) {
            System.out.println("第" + (i+1) + "个参数为:" + args[i]);
        }
        System.out.println("被代理的对象:" + joinPoint.getTarget());
        System.out.println("代理对象自己:" + joinPoint.getThis());

        String[] fieldsName = getFieldsName(joinPoint);
        for (int i = 0; i < fieldsName.length; i++) {
            System.out.println("第" + (i+1) + "个参数为:" + fieldsName[i]);
        }
    }

    /**
     * 环绕方法,可自定义目标方法执行的时机
     * @param pjd JoinPoint的子接口,添加了
     *            Object proceed() throws Throwable 执行目标方法
     *            Object proceed(Object[] var1) throws Throwable 传入的新的参数去执行目标方法
     *            两个方法
     * @return 此方法需要返回值,返回值视为目标方法的返回值
     */
    @Around("BrokerAspect()")
    public Object aroundMethod(ProceedingJoinPoint pjd){
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();

        boolean source = request.getHeader("source")!=null;
        Enumeration<String> headerNames = request.getHeaderNames();
        while(headerNames.hasMoreElements()){
            String s = headerNames.nextElement();
            System.out.println(s+":"+request.getHeader(s));
        }
        Object result = null;

        try {

            if (!source){
                Object[] args = pjd.getArgs();
                for (int i = 0; i < args.length; i++) {
                    if (args[i] instanceof User){
                        String userName = (String) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
                        if (StringUtils.isNotEmpty(userName)){
                            User user =userClient.AuthGetUserByUserName(userName);
                            args[i] = user;
                        }
                    }
                }
                result = pjd.proceed(args);
                result = new ResponseResult().success(result);
            }else{
                result = pjd.proceed();
            }


        } catch (Throwable e) {
            e.printStackTrace();
            result = new ResponseResult().failure(e.toString());
        }finally {
            return result;
        }

    }


    public String[] getFieldsName(JoinPoint joinPoint){
        Signature signature = joinPoint.getSignature();
        MethodSignature methodSignature = (MethodSignature) signature;
        // 通过这获取到方法的所有参数名称的字符串数组
        String[] parameterNames = methodSignature.getParameterNames();
        return parameterNames;
    }


}
