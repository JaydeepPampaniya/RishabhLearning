package org.example;

import org.springframework.context.ApplicationContext;;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App 
{
    public static void main( String[] args )
    {
        ApplicationContext context = new ClassPathXmlApplicationContext("spring.xml");
//        Programmer one = (Programmer) context.getBean("programmer1");

//        Programmer one = context.getBean("programmer1",Programmer.class);

        Programmer one = context.getBean(Programmer.class);
        one.code();

        Computer com = context.getBean(Computer.class); // declaration of primary is required

        Computer com2 = context.getBean(Desktop.class);
        Computer com3 = context.getBean(Laptop.class);

        System.out.println(one.getAge());

    }
}
