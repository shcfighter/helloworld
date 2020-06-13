package com.ecit;

import org.apache.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@EnableDubbo(scanBasePackages = "com.ecit")
@PropertySource("classpath:/spring/dubbo-consumer.properties")
public class AppConfig {
}
