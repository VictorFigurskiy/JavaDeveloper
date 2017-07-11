package com.victor.practice.modul02.dao.exampleHibernateJpa;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import java.io.File;

/**
 * Created by Sonikb on 20.06.2017.
 */

//Утильный класс для создания фабрики сессий на основе Hibernate в одном екземпляре на программу.
public class HibernateUtil {

    private static SessionFactory newSessionFactory;

    static {
        try {

            File file = new File("src\\main\\resources\\META-INF\\Hibernate.cfg.xml");

            Configuration configuration = new Configuration().configure();
            configuration.configure(file);

//            StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());

            newSessionFactory = configuration.buildSessionFactory();

        }catch (Throwable ex){
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static Session getSession(){
        return newSessionFactory.openSession();
    }

    public static void shutDown(){
        newSessionFactory.close();
    }
}
