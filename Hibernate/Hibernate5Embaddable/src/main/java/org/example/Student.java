package org.example;

import jakarta.persistence.*;


//there is a three layers of entity first one is classname second is entityname  and third is tablename by default is classname
//if you want to change table name u can use Table annotation
// if you are change the entity name so it will also change the table name if you dont mention a table annotation
@Entity
//@Table(name = "Student_table")
public class Student {
    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int rollNo;

//    @Transient // basically useful for remove the column this column is not saved in database

    private StudentFullName fullName;

//    @Column(name = "Student_Age")
    private int age;

    public int getRollNo() {
        return rollNo;
    }

    public void setRollNo(int rollNo) {
        this.rollNo = rollNo;
    }

    public StudentFullName getFullName() {
        return fullName;
    }

    public void setFullName(StudentFullName fullName) {
        this.fullName = fullName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Student{" +
                "rollNo=" + rollNo +
                ", fullName=" + fullName +
                ", age=" + age +
                '}';
    }
}
