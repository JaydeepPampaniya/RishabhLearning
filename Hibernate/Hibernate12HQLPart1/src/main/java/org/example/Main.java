package org.example;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import java.util.List;
import java.util.Random;


public class Main {
    public static void main(String[] args) {

        SessionFactory sf = new Configuration()
                .addAnnotatedClass(Student.class)
                .configure()
                .buildSessionFactory();

        //for level 2 caching
        Session session = sf.openSession();
        session.beginTransaction();

//        Random r = new Random();
//        for(int i=1; i<50; i++){
//            Student s = new Student();
//            s.setRollNo(i);
//            s.setName("name " + i);
//            s.setAge(r.nextInt(100));
//            session.persist(s);
//        }

        Query q1 = session.createQuery("from Student where age > 50");

//        List<Student> studentList =q1.list();
//        for (Student s1: studentList)
//            System.out.println(s1);


        Query q2 = session.createQuery("from Student where rollNo=20");
        Student s2 = (Student)q2.uniqueResult();
        System.out.println(s2);


        Query q3 = session.createQuery("select rollNo,name,age from Student where rollNo=20");
        Object[] student = (Object[])q3.uniqueResult();
        for(Object s:student)
            System.out.println(s);
        System.out.println(student[0] + ":" + student[1] +":"+ student[2]);

        Query q4 = session.createQuery("select rollNo,name,age from Student where rollNo>20");
        List<Object[]> students = (List<Object[]>)q4.list();
        for(Object[] o:students)
            System.out.println(o[0] + " " +o[1]+ " "+ o[2]);

        Query q5 = session.createQuery("select sum(age) from Student");
        Long age = (Long)q5.uniqueResult();
        System.out.println(age);



        Query query = session.createNativeQuery("select rollNo,name,age from student where age > 60", Student.class);
        List<Student> st = query.list();
        for(Student s: st)
            System.out.println(s);

        session.getTransaction().commit();
        session.close();
        sf.close();
    }
}