package org.example;

import org.springframework.context.ApplicationContext;;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App 
{
    public static void main( String[] args )
    {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
        Programmer one = (Programmer) context.getBean("programmer1");
        one.number=20;
        System.out.println(one.number);
//        one.code();

        Programmer two  = (Programmer) context.getBean("programmer1");
        System.out.println(two.number);
//        two.code();
    }
}
