package com.Programmer.Service;

import com.Programmer.Model.Student;
import com.Programmer.Repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService {

    private StudentRepository repo;

    public StudentRepository getRepo() {
        return repo;
    }

    @Autowired
    public void setRepo(StudentRepository repo) {
        this.repo = repo;
    }

    public void addStudent(Student student) {
        repo.save(student);
    }

    public List<Student> getStudent(){
        return repo.findAll();
    }
}
