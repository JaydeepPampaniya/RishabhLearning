package com.Programmer;

import com.Programmer.Model.Student;
import com.Programmer.Service.StudentService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.util.List;

@SpringBootApplication
public class SpringBoot1JdbCexApplication {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(SpringBoot1JdbCexApplication.class, args);

		Student student = context.getBean(Student.class);
		student.setRollNo(1);
		student.setName("Jaydeep");
		student.setMarks(12);

		StudentService service = context.getBean(StudentService.class);
		service.addStudent(student);

		List<Student> studentList = service.getStudent();
		System.out.println(studentList);

	}


}
