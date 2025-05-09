package com.Programmer.SpringBoot1Annotation;

import org.springframework.stereotype.Component;

@Component
public class Laptop implements Computer{
    @Override
    public void compile() {
        System.out.println("Compile from Laptop");
    }
}
