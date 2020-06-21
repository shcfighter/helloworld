package com.ecit;

import com.ecit.task.TestTask;
import org.activiti.spring.boot.SecurityAutoConfiguration;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.Resource;

/**
 * Hello world!
 */
@SpringBootApplication(exclude = SecurityAutoConfiguration.class,scanBasePackages="com.ecit")
public class ActivitiApplication implements CommandLineRunner {

    @Resource
    private TestTask testTask;

    public static void main(String[] args) {
        SpringApplication.run(ActivitiApplication.class, args);
        System.out.println("Hello World!");

    }

    @Override
    public void run(String... args) throws Exception {
        testTask.run();
    }
}
