package StaffOfSchool;

import general.Person;

import java.util.Objects;

public class Student extends Person {
    public Student(int rollNo,String name, String department){
        super(rollNo,name,department);
    }

    @Override
    public String toString() {
        return "RollNo: " + getId() + ", Name: " + getName() + ", Department: " + getDepartment();
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return getId() == student.getId();
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    public int getRollNo() {
        return getId();
    }
}
