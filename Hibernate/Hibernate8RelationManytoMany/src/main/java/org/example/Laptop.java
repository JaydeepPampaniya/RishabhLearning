package org.example;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;


import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Entity
public class Laptop {
    @Id
    private int lid;
    private String lname;

    @ManyToMany(mappedBy = "laptop")
    private List<Student> stud = new ArrayList<>();

    public int getLid() {
        return lid;
    }

    public void setLid(int lid) {
        this.lid = lid;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public List<Student> getStud() {
        return stud;
    }

    public void setStud(List<Student> stud) {
        this.stud = stud;
    }

    @Override
    public String toString() {
        return "Laptop{" +
                "lid=" + lid +
                ", lname='" + lname + '\'' +
                ", stud=" + stud +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Laptop laptop = (Laptop) o;
        return lid == laptop.lid && Objects.equals(lname, laptop.lname);
    }

    @Override
    public int hashCode() {
        return Objects.hash(lid, lname);
    }

}
