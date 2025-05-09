package com.Programmer.Repository;

import com.Programmer.Model.Student;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class StudentRepository {
    List<Student> list = new ArrayList<>();
    public void save(Student student) {
        list.add(student);
    }

    public List<Student> findAll(){

        return list;
    }
}