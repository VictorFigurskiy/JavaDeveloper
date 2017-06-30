package com.victor.practice.modul02.dao.exampleHibernateJpa;

import com.victor.practice.modul02.instance.Skills;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 * Created by Sonikb on 20.06.2017.
 */

//Тест работы с сессиями и транзакциями в Hibernate
public class HibernateTest {
    public static void main(String[] args) {
        Session session = HibernateUtil.getSession();
        Transaction transaction = session.getTransaction();
        transaction.begin();
        Skills skills = new Skills();
        skills.setSkillName("MVC");
        session.save(skills);
        transaction.commit();
        HibernateUtil.shutDown();
    }
}
