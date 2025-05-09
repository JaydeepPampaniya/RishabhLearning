package org.example;

import org.springframework.stereotype.Component;


public class Programmer {
    private int age;
    private Laptop laptop;
    private Desktop desktop;

    public Programmer(){
        System.out.println("object is created");
    }

//    public Desktop getDesktop() {
//        return desktop;
//    }
//
//    public void setDesktop(Desktop desktop) {
//        this.desktop = desktop;
//    }

    public Programmer(int age, Laptop laptop, Desktop desktop) {
        this.age = age;
        this.laptop = laptop;
        this.desktop = desktop;
    }

//    public Laptop getLaptop() {
//        return laptop;
//    }

    public void code(){
        System.out.println("coding");
        laptop.compile();
        desktop.compile();
    }

    public int getAge() {
        return age;
    }

//    public void setAge(int age) {
//        this.age = age;
//    }
//
//    public void setLaptop(Laptop laptop) {
//        this.laptop = laptop;
//    }
}
