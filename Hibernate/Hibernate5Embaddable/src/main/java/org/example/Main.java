package org.example;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;


public class Main {
    public static void main(String[] args) {

        StudentFullName name = new StudentFullName();
        name.setfName("Jaydeep");
        name.setmName("Hamirbhai");
        name.setlName("Pampaniya");

        Student s1 = new Student();
        s1.setRollNo(1);
        s1.setFullName(name);
        s1.setAge(20);

        Student s2 = null;
        SessionFactory sf = new Configuration()
                .addAnnotatedClass(Student.class)
                .configure()
                .buildSessionFactory();


        Session session = sf.openSession();
        s2 = session.get(Student.class, 1);

        Transaction trasaction = session.beginTransaction();

        session.persist(s1);


        trasaction.commit();

        session.close();
        sf.close();
        System.out.println(s2);
    }
}