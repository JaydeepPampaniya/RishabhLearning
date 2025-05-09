package org.example;

import org.springframework.stereotype.Component;


public class Programmer {
    private int age;

    public Programmer(){
        System.out.println("object is created");
    }
    public void code(){
        System.out.println("codeing");
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }


}
