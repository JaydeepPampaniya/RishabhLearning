package org.example;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

//@Component("laptop")
@Component
@Primary

public class Laptop implements Computer{
    public Laptop(){
        System.out.println("Laptop object is created");
    }
    @Override
    public void compile(){
        System.out.println("compile from laptop");
    }
}
