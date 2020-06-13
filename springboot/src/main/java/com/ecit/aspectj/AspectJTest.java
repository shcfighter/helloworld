package com.ecit.aspectj;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class AspectJTest {

    /*@Pointcut("@annotation(com.ecit.aspectj.MyInfoAnnotation)")
    public void test(){
    }*/

    // 用@Pointcut来注解一个切入方法
    @Pointcut("execution(* com.ecit.controller.*.*(..))")
    public void test() {
    }


    @Before("test()")
    public void beforeTest(){
        System.out.println("beforeTest");
    }
    
    @Around("test()")
    public Object aroundTest(ProceedingJoinPoint p){
        System.out.println("around.....before");
        Object o = null;
        try{
            o = p.proceed();
        }catch(Throwable e){
            e.printStackTrace();
        }
        System.out.println("around.....after");
        return o;
    }
    
    @After("test()")
    public void afterTest()
    {
        System.out.println("afterTest");
    }
 }