package com.hand.hls.sys.component;

import com.hand.hls.sys.dto.User;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Component
@Aspect
public class LoggingAspect {

    @Pointcut("execution(*  com.hand.hls.sys.service.impl.*.*(..))")
    public void pointCut() {
    }


    @Before("pointCut()")
    public void beforeMethod(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName();


        //            方法的签名： User com.hand.hls.sys.service.IUserService.getUserById(int)
        //            方法名： getUserById
        //            类名： com.hand.hls.sys.service.IUserService
        //            参数类型： interface com.hand.hls.sys.service.IUserService
        //            详细签名： public abstract com.hand.hls.sys.dto.User com.hand.hls.sys.service.IUserService.getUserById(int)
        //            简洁签名： IUserService.getUserById(..)
        //        System.out.println("方法的签名： " + joinPoint.getSignature());
        //        System.out.println("方法名： " + joinPoint.getSignature().getName());
        //        System.out.println("类名： " + joinPoint.getSignature().getDeclaringTypeName());
        //        System.out.println("参数类型： " + joinPoint.getSignature().getDeclaringType());
        //        System.out.println("详细签名： " + joinPoint.getSignature().toLongString());
        //        System.out.println("简洁签名： " + joinPoint.getSignature().toShortString());
        //        System.out.println("切点类型" + joinPoint.getKind());
        //        System.out.println("切点类型 + 详细签名" + joinPoint.toLongString());
        //        System.out.println("切点类型 + 简洁签名" + joinPoint.toShortString());

        //"入参"
        List<Object> args = Arrays.asList(joinPoint.getArgs());
        System.out.println("方法前调用 The method " + methodName + " begins " + args);
    }

    @After("pointCut()")
    public void afterMethod(JoinPoint joinPoint) {
        String methodName = joinPoint.getSignature().getName();
        List<Object> args = Arrays.asList(joinPoint.getArgs());
        System.out.println("方法后调用 The method " + methodName + " end " + args);
    }

    @AfterReturning(pointcut = "pointCut()", returning = "returnVal")
    public void afterReturning(JoinPoint joinPoint, Object returnVal) {
        String methodName = joinPoint.getSignature().getName();
        //返回值
        User user = (User) returnVal;
        System.out.println("方法有返回值后调用 The method " + methodName + " end with " + user.getUserName());
    }

    @AfterThrowing(pointcut = "pointCut()", throwing = "error")
    public void afterThrowting(JoinPoint joinPoint, Exception error) {
        String methodName = joinPoint.getSignature().getName();
        System.out.println("方法异常后调用 The method " + methodName + " occurs exceptions " + error);
    }

    @Around("pointCut()")
    public Object around(ProceedingJoinPoint proceedingJoinPoint) {

        Object result = null;
        String methodName = proceedingJoinPoint.getSignature().getName();

        //执行目标方法
        try {
            //前置通知
            System.out.println("The method " + methodName + "begin with" + Arrays.asList(proceedingJoinPoint.getArgs()));

            result = proceedingJoinPoint.proceed();

            //后置通知
            System.out.println("The method " + methodName + "end with" + result);

        } catch (Throwable e) {
            //异常通知
            System.out.println("The method occurs exception : " + e);
            throw new RuntimeException();
        }
        //后置通知

        System.out.println("The method " + methodName + "end with" + result);

        return result;

    }
}
