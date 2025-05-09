package com.Programmer.SpringBoot1Annotation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class SpringBoot1AnnotationApplication {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(SpringBoot1AnnotationApplication.class, args);
		Programmer p1 = context.getBean(Programmer.class);
		p1.code();
		System.out.println(p1.getAge());
	}

}
