package com.victor.practice.modul02.dao.exampleHibernateJpa;


import com.victor.practice.modul02.instance.Skills;
import org.hibernate.Session;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Created by Sonikb on 21.06.2017.
 */
public class JPA {
    public static void main(String[] args) {
//        Configuration configuration = new Configuration();
//        configuration.configure("src\\main\\resources\\META-INF\\Hibernate.cfg.xml");
//
//        EntityManagerFactory entityFactory = configuration.buildSessionFactory();
//        EntityManager entityManager1 = entityFactory.createEntityManager();
//        entityManager1.getTransaction().begin();
//        entityManager1.getTransaction().commit();
//        entityManager1.close();

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("hibernate");
        EntityManager entityManager = entityManagerFactory.createEntityManager();

        Session session = (Session) entityManager;
        session.getTransaction().begin();
        Skills skills = new Skills();
        skills.setSkillName("Web");
        session.save(skills);

        session.getTransaction().commit();
        session.close();
        entityManagerFactory.close();
    }
}
