package com.programmer.spring_data_jpa_ex;

import com.programmer.spring_data_jpa_ex.model.Student;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

import java.util.List;
import java.util.Optional;

@SpringBootApplication
public class SpringDataJpaExApplication {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(SpringDataJpaExApplication.class, args);

		Student s1= context.getBean(Student.class);
		Student s2=context.getBean(Student.class);
		Student s3=context.getBean(Student.class);
		Student s4=context.getBean(Student.class);


		StudentRepo repo=context.getBean(StudentRepo.class);

		s1.setRollNo(101);
		s1.setName("Jaydeep");
		s1.setMarks(21);


		s2.setRollNo(102);
		s2.setName("aashish");
		s2.setMarks(90);


		s3.setRollNo(103);
		s3.setName("Harsh");
		s3.setMarks(70);

		s4.setRollNo(103);
		s4.setName("kapil");
		s4.setMarks(96);

//		repo.save(s1);
//		repo.save(s2);
//		repo.save(s3);

//		List<Student> list =  repo.findAll();
//		for(Student list1: list)
//			System.out.println(list1);

//		System.out.println(repo.findById(101));
//		Optional<Student> s = repo.findById(104);
//		System.out.println(s.orElse(new Student()));
//		List<Student> students = repo.findByNameWithMarks("jaydeep",21);
//		System.out.println(students);


//		System.out.println("findByName" + repo.findByName("jaydeep"));
//
//
//		List<Student> students = repo.findByMarksGreaterThan(1);
//		for(Student s: students)
//			System.out.println(s);
//
//		System.out.println("findByNameWithMarks"+ repo.findByNameWithMarks("jaydeep",21));
//
//		System.out.println("findByMarksGreaterThan"+repo.findByMarksGreaterThan(50));

//		repo.save(s2);
		repo.save(s4);

		repo.delete(s4);
	}

}
