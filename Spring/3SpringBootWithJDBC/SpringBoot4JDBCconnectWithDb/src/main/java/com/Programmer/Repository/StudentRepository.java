package com.Programmer.Repository;

import com.Programmer.Model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class StudentRepository {
    private JdbcTemplate jdbc;

    public void save(Student student) {
        String query = "insert into student(rollNo,name,marks) values(?,?,?)";
        int rows = jdbc.update(query,student.getRollNo(),student.getName(),student.getMarks());
        System.out.println(rows+" rows affected");
    }

    public JdbcTemplate getJdbc() {
        return jdbc;
    }

    @Autowired
    public void setJdbc(JdbcTemplate jdbc) {
        this.jdbc = jdbc;
    }

    public List<Student> findAll(){
        String query = "select * from student";
   // step1
        //RowMapper<Student> map = new RowMapper<Student>() {
//            @Override
//            public Student mapRow(ResultSet rs, int rowNum) throws SQLException {
//                Student s = new Student();
//                s.setRollNo(rs.getInt("rollNo"));
//                s.setName(rs.getString("name"));
//                s.setMarks(rs.getInt("marks"));
//                return s;
//            }
//        };
//
//        return jdbc.query(query,map);

            //step2
        return jdbc.query(query,(rs,rowNum)->{
            Student s = new Student();
                s.setRollNo(rs.getInt("rollNo"));
                s.setName(rs.getString("name"));
                s.setMarks(rs.getInt("marks"));
                return s;
        });
    }
}