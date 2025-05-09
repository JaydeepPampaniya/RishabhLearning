package org.example;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;


public class Main {
    public static void main(String[] args) {

        SessionFactory sf = new Configuration()
                .addAnnotatedClass(Student.class)
                .configure()
                .buildSessionFactory();

        //for level 2 caching
        Session session2 = sf.openSession();
        session2.beginTransaction();

        Query q1 = session2.createQuery("from Student where rollNo = 3324");
        q1.uniqueResult();
        q1.setCacheable(true);
        System.out.println(q1.list());

        session2.getTransaction().commit();
        session2.close();

        Session session3 = sf.openSession();
        session3.beginTransaction();
        Query q2 = session3.createQuery("from Student where rollNo = 3324");
        q2.setCacheable(true);
        q2.uniqueResult();
        System.out.println(q2.list());

        session3.getTransaction().commit();
        session3.close();
        sf.close();
    }
}