package org.example;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;


public class Main {
    public static void main(String[] args) {
        Laptop l1 = new Laptop();
        l1.setLid(1);
        l1.setLname("lenovo");

        Student s = new Student();
        s.setRollNo(1);
        s.setName("Jaydeep");
        s.setMarks(89);
        s.setLaptop(l1);

        SessionFactory sf = new Configuration()
                .addAnnotatedClass(Student.class)
                .addAnnotatedClass(Laptop.class)
                .configure()
                .buildSessionFactory();


        Session session = sf.openSession();

        Transaction trasaction = session.beginTransaction();

        session.persist(l1);
        session.persist(s);


        trasaction.commit();

        session.close();
        sf.close();
    }
}