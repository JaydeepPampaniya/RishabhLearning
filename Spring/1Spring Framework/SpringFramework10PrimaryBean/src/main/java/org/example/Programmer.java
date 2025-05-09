package org.example;

import org.springframework.stereotype.Component;


public class Programmer {
    private int age;

    private Computer com;

    public Programmer(){
        System.out.println("object is created");
    }


    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Computer getCom() {
        return com;
    }

    public void setCom(Computer com) {
        this.com = com;
    }
    public void code() {
        System.out.println("Coding");
        com.compile();
    }
}
