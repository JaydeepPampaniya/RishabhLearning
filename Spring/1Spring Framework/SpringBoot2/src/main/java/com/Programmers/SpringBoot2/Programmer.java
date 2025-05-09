package com.Programmers.SpringBoot2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Programmer {
    @Autowired
    Laptop laptop;
    public void code(){
        laptop.compile();
    }
}
