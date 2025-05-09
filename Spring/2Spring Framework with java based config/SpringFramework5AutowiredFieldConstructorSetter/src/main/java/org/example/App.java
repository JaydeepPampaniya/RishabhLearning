package org.example;

import org.example.config.AppConfig;
import org.springframework.context.ApplicationContext;;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App 
{
    public static void main( String[] args )
    {
        ApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
        Programmer d1 = context.getBean(Programmer.class);
        d1.code();
        Desktop d2 = context.getBean(Desktop.class);
        d2.compile();

    }
}
