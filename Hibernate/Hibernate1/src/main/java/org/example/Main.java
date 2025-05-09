package org.example;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;


public class Main {
    public static void main(String[] args) {
        Student s1 = new Student();
        s1.setName("Aashiah");
        s1.setAge(20);

        Configuration cg = new Configuration();
        cg.addAnnotatedClass(Student.class);
        cg.configure();


        SessionFactory sf = cg.buildSessionFactory();
        Session session = sf.openSession();
        Transaction transaction = session.beginTransaction();
//        session.save(s1);
        session.persist(s1); //recomonded and optimized instead of save

        transaction.commit();
        session.close();
        sf.close();
    }
}