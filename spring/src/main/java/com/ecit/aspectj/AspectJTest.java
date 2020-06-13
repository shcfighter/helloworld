package com.ecit.aspectj;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Aspect
@Order(10)
public class AspectJTest {

    /*@Pointcut("execution(* com.ecit.*.*.*(..))")
    public void test(){
    }*/

    @Pointcut("@annotation(com.ecit.aspectj.MyInfoAnnotation)")
    public void test(){
    }
    
    @Before("test()")
    public void beforeTest(){
        System.out.println("AspectJTest1 beforeTest");
    }

    @Before("test()")
    public void beforeTest2(){
        System.out.println("AspectJTest1 beforeTest2");
    }
    
    @Around("test()")
    public Object aroundTest(ProceedingJoinPoint p){
        System.out.println("AspectJTest1 around.....before");
        Object o = null;
        try{
            o = p.proceed();
        }catch(Throwable e){
            e.printStackTrace();
        }
        System.out.println("AspectJTest1 around.....after");
        return o;
    }
    
    @After("test()")
    public void afterTest()
    {
        System.out.println("AspectJTest1 afterTest");
    }
 }