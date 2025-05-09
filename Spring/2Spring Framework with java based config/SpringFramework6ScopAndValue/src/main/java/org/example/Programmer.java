package org.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class Programmer {
    @Value("21")
    private int age;

    @Autowired
    @Qualifier("laptop") // you can apply primary on laptop class
    private Computer com;

    public Programmer(){
        System.out.println("programmer object is created");
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

//    @Autowired
    public void setCom(Computer com) {
        this.com = com;
    }
    public void code() {
        System.out.println("Coding");
        com.compile();
    }
}
