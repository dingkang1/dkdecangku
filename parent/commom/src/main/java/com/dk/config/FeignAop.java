package com.dk.config;

import com.alibaba.fastjson.JSONObject;
import com.dk.annotiation.ResponseDataType;
import com.dk.entity.ResultVo;
import com.dk.feign.FeignServier;
import com.dk.server.ThreadLocalServer;
import com.dk.utils.CommonServerUtils;
import feign.Response;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.aop.framework.AdvisedSupport;
import org.springframework.aop.framework.AopProxy;
import org.springframework.aop.support.AopUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

@Component
@Aspect
@EnableAspectJAutoProxy(proxyTargetClass = true, exposeProxy = true)
public class FeignAop {

    @Autowired
    FeignServier feignServier ;

    @Autowired
    ThreadLocalServer threadLocalServer;
    @Pointcut("execution(* com.dk.feign..*(..))")
    public void aopPoint(){};


    @Before("aopPoint()")
    public void before(JoinPoint joinPoint){
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        Method method = signature.getMethod();
        ResponseDataType annotation = method.getAnnotation(ResponseDataType.class);
        if(null != annotation){
            threadLocalServer.put("responseDataType",annotation.value());
        }
    }

    @AfterReturning(returning = "object",value = "aopPoint()")
    public Object afterReturn(Object object){

     /*   Object o = AopContext.currentProxy();
        Class<?>[] interfaces = o.getClass().getInterfaces();*/
        Response response = null ;
        if( object instanceof Response){
            response = (Response)object;
        }
        if(object instanceof FeignServier.FeignServierApi){
            return object ;
        }
        ResultVo<String> resultVo = CommonServerUtils.getResultVo(response, (Class)threadLocalServer.get("responseDataType"));
        threadLocalServer.put(ThreadLocalServer.DATA,resultVo);
        return resultVo;
    }

    public Object getTarget(Object proxy) throws Exception {

                if(!AopUtils.isAopProxy(proxy)) {
                        //不是代理对象
                        return proxy;
                }
                if(AopUtils.isJdkDynamicProxy(proxy)) {
                         return getJdkDynamicProxyTargetObject(proxy);
                     } else { //cglib
                       return getCglibProxyTargetObject(proxy);
                    }
             }

              private Object getCglibProxyTargetObject(Object proxy) throws Exception {
                Field h = proxy.getClass().getDeclaredField("CGLIB$CALLBACK_0");
                 h.setAccessible(true);
                 Object dynamicAdvisedInterceptor = h.get(proxy);
                Field advised = dynamicAdvisedInterceptor.getClass().getDeclaredField("advised");
                 advised.setAccessible(true);
                 Object target = ((AdvisedSupport)advised.get(dynamicAdvisedInterceptor)).getTargetSource().getTarget();
                return target;
            }
            private Object getJdkDynamicProxyTargetObject(Object proxy) throws Exception {
                Field h = proxy.getClass().getSuperclass().getDeclaredField("h");
                 h.setAccessible(true);
                 AopProxy aopProxy = (AopProxy) h.get(proxy);
                 Field advised = aopProxy.getClass().getDeclaredField("advised");
                 advised.setAccessible(true);
                 Object target = ((AdvisedSupport)advised.get(aopProxy)).getTargetSource().getTarget();
                 return target;
             }
}
