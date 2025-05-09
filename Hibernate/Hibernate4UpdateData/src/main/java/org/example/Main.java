package org.example;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;


public class Main {
    public static void main(String[] args) {
        Student s1 = new Student();
        s1.setRollNo(3325);
        s1.setName("aashish");
        s1.setAge(20);

        Student s2 = null;
        SessionFactory sf = new Configuration()
                .addAnnotatedClass(Student.class)
                .configure()
                .buildSessionFactory();


        Session session = sf.openSession();
        s2 = session.get(Student.class, 3323);

        Transaction trasaction = session.beginTransaction();

        session.merge(s1);
        session.remove(s2);

        trasaction.commit();

        session.close();
        sf.close();
        System.out.println(s2);
    }
}