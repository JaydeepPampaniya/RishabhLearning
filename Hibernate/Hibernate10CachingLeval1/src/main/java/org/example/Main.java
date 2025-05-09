package org.example;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;


public class Main {
    public static void main(String[] args) {
        Student s1 = new Student();
        s1.setName("Aashiah");
        s1.setAge(20);

        Student s2 = null;
        SessionFactory sf = new Configuration()
                .addAnnotatedClass(Student.class)
                .configure()
                .buildSessionFactory();

        Session session1 = sf.openSession();

        session1.beginTransaction();
        s2 = session1.get(Student.class, 3324);
        System.out.println(s2);

        s2 = session1.get(Student.class,3324);
        System.out.println(s2);

        session1.getTransaction().commit();
        session1.close();


        //for level 2 caching

        Student s3 = null;
        Session session2 = sf.openSession();
        session2.beginTransaction();

        s3 = session1.get(Student.class,3324);
        System.out.println(s3);

        session2.getTransaction().commit();
        session2.close();

        Session session3 = sf.openSession();
        session3.beginTransaction();

        s3 = session1.get(Student.class,3324);
        System.out.println(s3);

        session3.getTransaction().commit();
        session3.close();
        sf.close();
    }
}