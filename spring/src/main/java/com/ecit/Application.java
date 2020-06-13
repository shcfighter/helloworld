package com.ecit;

import com.ecit.service.IUserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Hello world!
 *
 */
public class Application
{
    public static void main( String[] args )
    {
        AnnotationConfigApplicationContext annotationConfigApplicationContext = new AnnotationConfigApplicationContext();
        annotationConfigApplicationContext.register(AppConfig.class);
        annotationConfigApplicationContext.refresh();
        IUserService userService = (IUserService) annotationConfigApplicationContext.getBean("userService");
        System.out.println(userService.getUser("张三"));
        //System.out.println(userService.getUser2("张三2"));

        //System.out.println(annotationConfigApplicationContext.getBean("myFactorybean").getClass());
        //System.out.println(annotationConfigApplicationContext.getBean("&myFactorybean").getClass());

    }
}
