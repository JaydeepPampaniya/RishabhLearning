package org.example;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;


@Entity
public class Student {
    @Id
    private int rollNo;
    private List<Laptop> laptop = new ArrayList<Laptop>();
    private String name;
    private int marks;

    @OneToMany(mappedBy = "stud",fetch = FetchType.EAGER)// if you don't mention 'stud' it will create a new table student_laptop;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRollNo() {
        return rollNo;
    }

    public void setRollNo(int rollNo) {
        this.rollNo = rollNo;
    }

    public int getMarks() {
        return marks;
    }

    public void setMarks(int marks) {
        this.marks = marks;
    }

    public List<Laptop> getLaptop() {
        return laptop;
    }

    public void setLaptop(List<Laptop> laptop) {
        this.laptop = laptop;
    }

    @Override
    public String toString() {
        return "Student{" +
                "rollNo=" + rollNo +
                ", name='" + name + '\'' +
                ", marks=" + marks +
                '}';
    }
}
