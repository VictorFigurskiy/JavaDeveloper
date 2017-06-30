package com.victor.practice.modul02.dao.jpaHibernateDAO;

import com.victor.practice.modul02.dao.DeveloperDAO;
import com.victor.practice.modul02.dao.simpleLogger.ExceptionLogger;
import com.victor.practice.modul02.instance.Developer;
import com.victor.practice.modul02.instance.Project;
import org.apache.log4j.Logger;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Sonikb on 27.06.2017.
 */
public class JpaDeveloperDAO implements DeveloperDAO {

    private static final Logger logger = Logger.getLogger(JpaDeveloperDAO.class);

    @Override
    public Developer save(Developer developer) {
        EntityManager em = null;
        try {
            em = JpaUtil.getEntityManager();
            em.getTransaction().begin();
            Project project = em.find(Project.class, developer.getProjectID());
            developer.setProject(project);
            em.persist(developer);
            em.getTransaction().commit();
        } catch (RuntimeException e) {
            if (em != null) {
                em.getTransaction().rollback();
            }
            logger.error(e);
        } finally {
            if (em != null) {
                em.close();
            }
        }
        return developer;
    }

    @Override
    public Developer read(int id) {
        EntityManager em = null;
        Developer developer = null;
        try {
            em = JpaUtil.getEntityManager();
            em.getTransaction().begin();
            developer = em.find(Developer.class, id);
            em.getTransaction().commit();
        } catch (RuntimeException e) {
            if (em != null) {
                em.getTransaction().rollback();
            }
            logger.error(e);
        } finally {
            if (em != null) {
                em.close();
            }
        }
        return developer;
    }

    @Override
    public void update(int id, Developer developer) {
        EntityManager em = null;
        try {
            em = JpaUtil.getEntityManager();
            em.getTransaction().begin();
            developer.setId(id);
            em.merge(developer);
            em.getTransaction().commit();
        } catch (RuntimeException e) {
            if (em != null) {
                em.getTransaction().rollback();
            }
            logger.error(e);
        } finally {
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
            em.remove(em.find(Developer.class, id));
            em.getTransaction().commit();
        } catch (RuntimeException e) {
            if (em != null) {
                em.getTransaction().rollback();
            }
            logger.error(e);
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    @Override
    public List<Developer> readAllTable() {
        EntityManager em = null;
        List<Developer> developerList = new ArrayList<>();
        try {
            em = JpaUtil.getEntityManager();
            em.getTransaction().begin();
            TypedQuery<Developer> namedQuery = em.createNamedQuery("Developer.findAll", Developer.class);
            developerList = namedQuery.getResultList();
            em.getTransaction().commit();
        } catch (RuntimeException e) {
            if (em != null) {
                em.getTransaction().rollback();
            }
            logger.error(e);
        } finally {
            if (em != null) {
                em.close();
            }
        }
        return developerList;
    }
}
