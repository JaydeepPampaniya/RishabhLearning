package com.Programmer.SpringBoot1Annotation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Programmer {
    @Value("21")
    private int age;

//    @Autowired
//    @Qualifier("desktop")
    private Computer com;

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Computer getCom() {
        return com;
    }

    @Autowired
    @Qualifier("desktop")
    public void setCom(Computer com) {
        this.com = com;
    }
    public void code() {
        com.compile();
    }
}
