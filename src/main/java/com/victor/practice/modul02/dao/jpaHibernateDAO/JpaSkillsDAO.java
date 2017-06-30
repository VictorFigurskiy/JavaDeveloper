package com.victor.practice.modul02.dao.jpaHibernateDAO;

import com.victor.practice.modul02.dao.SkillsDAO;
import com.victor.practice.modul02.instance.Skills;
import org.apache.log4j.Logger;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sonikb on 27.06.2017.
 */
public class JpaSkillsDAO implements SkillsDAO {

    private static final Logger logger = Logger.getLogger(JpaSkillsDAO.class);

    @Override
    public Skills save(Skills skills) {
        EntityManager em = null;
        try{
            em = JpaUtil.getEntityManager();
            em.getTransaction().begin();
            em.persist(skills);
            em.getTransaction().commit();
        }catch (RuntimeException e){
            if (em != null) {
                em.getTransaction().rollback();
            }
            logger.error(e);
        }finally {
            if (em != null) {
                em.close();
            }
        }
        return skills;
    }

    @Override
    public Skills read(int id) {
        EntityManager em = null;
        Skills skill = null;
        try {
            em = JpaUtil.getEntityManager();
            em.getTransaction().begin();
            skill = em.find(Skills.class, id);
            em.getTransaction().commit();
        }catch (RuntimeException e){
            if (em != null) {
                em.getTransaction().rollback();
            }
            logger.error(e);
        }finally {
            if (em != null) {
                em.close();
            }
        }
        return skill;
    }

    @Override
    public void update(int id, Skills skills) {
        EntityManager em = null;
        try {
            em = JpaUtil.getEntityManager();
            em.getTransaction().begin();
            skills.setId(id);
            em.merge(skills);
            em.getTransaction().commit();
        }catch (RuntimeException e){
            if (em != null) {
                em.getTransaction().rollback();
            }
            logger.error(e);
        }finally {
            if (em != null) {
                em.close();
            }
        }
    }

    @Override
    public void delete(int id) {
        EntityManager em = null;
        try {
            em = JpaUtil.getEntityManager();
            em.getTransaction().begin();
            Skills skill = em.find(Skills.class,id);
            em.remove(skill);
            em.getTransaction().commit();
        }catch (RuntimeException e){
            if (em != null) {
                em.getTransaction().rollback();
            }
            logger.error(e);
        }finally {
            if (em != null) {
                em.close();
            }
        }
    }

    @Override
    public List<Skills> readAllTable() {
        EntityManager em = null;
        List<Skills> skillsList = new ArrayList<>();
        try {
            em = JpaUtil.getEntityManager();
            em.getTransaction().begin();
            TypedQuery<Skills> namedQuery = em.createNamedQuery("Skills.findAll", Skills.class);
            skillsList = namedQuery.getResultList();
            em.getTransaction().commit();
        }catch (RuntimeException e){
            if (em != null) {
                em.getTransaction().rollback();
            }
            logger.error(e);
        }finally {
            if (em != null) {
                em.close();
            }
        }
        return skillsList;
    }
}
