package com.ecit;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

/**
 * Hello world!
 *
 */
@SpringBootApplication
@MapperScan("com.ecit.mapper")
@EnableCaching
public class Application
{
    public static void main( String[] args )
    {
        SpringApplication.run(Application.class);
    }
}
