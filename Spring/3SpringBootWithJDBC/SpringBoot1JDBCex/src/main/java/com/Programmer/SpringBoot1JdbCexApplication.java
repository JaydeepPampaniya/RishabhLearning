package com.Programmer;

import com.Programmer.Model.Student;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class SpringBoot1JdbCexApplication {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(SpringBoot1JdbCexApplication.class, args);

		Student student = context.getBean(Student.class);
		System.out.println(student);

	}


}
