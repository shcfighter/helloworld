package com.ecit;

import com.ecit.service.A;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;

/**
 * Hello world!
 */
@EnableAspectJAutoProxy
@SpringBootApplication
@EnableTransactionManagement
public class ZApplication {

    @Resource
    A a;

    public static void main(String[] args) {
        SpringApplication.run(ZApplication.class, args);
        System.out.println("Hello World!");
    }

    @PostConstruct
    public void aopTest() {
        a.h1();
    }
}
