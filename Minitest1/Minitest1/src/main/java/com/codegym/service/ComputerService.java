package com.codegym.service;

import org.springframework.stereotype.Service;

import com.codegym.model.Computer;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;

@Service
public class ComputerService implements IComputerService{
    private static SessionFactory sessionFactory;
    private static EntityManager entityManager;

    static {
        try {
            sessionFactory = new Configuration()
                    .configure("hibernate.conf.xml")
                    .buildSessionFactory();
            entityManager = sessionFactory.createEntityManager();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Computer> findAll() {
        String queryStr = "select p from Computer as p";
        TypedQuery<Computer> query = entityManager.createQuery(queryStr,Computer.class);
        return query.getResultList();
    }
    @Override
    public void save(Computer computer) {
        Transaction transaction = null;
        Computer origin;
        if (computer.getId() == 0){
            origin = new Computer();
        }else {
            origin = findById(computer.getId());
        }
        try (Session session = sessionFactory.openSession()) {
            transaction = session.beginTransaction();
            origin.setCode(computer.getCode());
            origin.setName(computer.getName());
            origin.setProducer(computer.getProducer());
            origin.setImg(computer.getImg());
            session.saveOrUpdate(origin);
            transaction.commit();
        }catch (Exception e){
            e.printStackTrace();
            if (transaction != null){
                transaction.rollback();
            }
        }
    }

    @Override
    public Computer findById(int id) {
        String queryStr = "SELECT p FROM Computer AS p WHERE p.id = :id";
        TypedQuery<Computer> query = entityManager.createQuery(queryStr, Computer.class);
        query.setParameter("id", id);
        return query.getSingleResult();
    }

    @Override
    public void remove(int id) {
        Computer computer = findById(id);
        if (computer != null) {
            Transaction transaction = null;
            try (Session session = sessionFactory.openSession()) {
                transaction = session.beginTransaction();
                session.remove(computer);
                transaction.commit();
            } catch (Exception e) {
                e.printStackTrace();
                if (transaction != null) {
                    transaction.rollback();
                }
            }
        }
    }
}
