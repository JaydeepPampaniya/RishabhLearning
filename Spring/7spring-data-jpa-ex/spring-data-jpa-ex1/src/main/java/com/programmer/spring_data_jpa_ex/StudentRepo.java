package com.programmer.spring_data_jpa_ex;

import com.programmer.spring_data_jpa_ex.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepo extends JpaRepository<Student,Integer> {

    List<Student> findByName(String name);
    List<Student> findByMarksGreaterThan(int i);
    List<Student> findByNameAndMarks(String name,int marks);

    @Query("select s from Student s where s.name = ?1 or s.marks = ?2")
    List<Student> findByNameWithMarks(String name,int marks);

    


}
