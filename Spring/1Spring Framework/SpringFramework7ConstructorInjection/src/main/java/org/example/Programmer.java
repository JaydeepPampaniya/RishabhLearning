package org.example;

import org.springframework.stereotype.Component;


public class Programmer {
    private int age;
    private Laptop laptop;
    public Programmer(){
        System.out.println("object is created");
    }

    public Programmer(int age, Laptop laptop) {
        this.age = age;
        this.laptop = laptop;
    }

    public Laptop getLaptop() {
        return laptop;
    }

    public void code(){
        System.out.println("coding");
        laptop.compile();
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setLaptop(Laptop laptop) {
        this.laptop = laptop;
    }
}
