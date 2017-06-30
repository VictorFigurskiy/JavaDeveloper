package com.victor.practice.modul02.dao.jpaHibernateDAO;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Created by Sonikb on 26.06.2017.
 */
public class JpaUtil {

    private static EntityManagerFactory entityManagerFactory;

    static {
        try {

            entityManagerFactory = Persistence.createEntityManagerFactory("hibernate");

        }catch (Throwable ex){
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static EntityManager getEntityManager(){
        return entityManagerFactory.createEntityManager();
    }

    public static void shutDown(){
        entityManagerFactory.close();
    }
}
