package com.Programmer.SpringBoot1Annotation;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@Primary
public class Desktop implements Computer{
    @Override
    public void compile() {
        System.out.println("Compile from Desktop");
    }
}
