package com.oleksa.model.dao.impl;

import java.util.List;
import java.util.Optional;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.oleksa.model.dao.UserDao;
import com.oleksa.model.entity.User;
import com.oleksa.model.util.Hibernate;

public class UserDaoImpl implements UserDao {

    @Override
    public User create(User t) throws Exception {
        try (Session session = Hibernate.getSession()) {
            Transaction transaction = session.beginTransaction();
            Long id = (Long) session.save(t);
            System.out.println(id);
            t.setId(id);
            transaction.commit();
            return t;
        }
    }

    @Override
    public void deleteById(Long id) {
        try (Session session = Hibernate.getSession()) {
            Transaction transaction = session.beginTransaction();
            Query query = session.createQuery("delete from User where id = :id");
            query.setParameter("id", id);
            query.executeUpdate();
            transaction.commit();
        }
    }

    @Override
    public User update(User t) throws Exception {
        try (Session session = Hibernate.getSession()) {
            Transaction transaction = session.beginTransaction();
            session.update(t);
            transaction.commit();
            return t;
        }
    }

    @Override
    public Optional<User> findById(Long id) {
        try (Session session = Hibernate.getSession()) {
            return Optional.ofNullable(session.find(User.class, id));
        }
    }

    @Override
    public List<User> findAll() {
        try (Session session = Hibernate.getSession()) {
            Transaction transaction = session.beginTransaction();
            org.hibernate.query.Query<User> query = session.createQuery("from User", User.class);
            transaction.commit();
            return query.getResultList();
        }
    }

    @Override
    public Optional<User> findByEmail(String email) {
        try (Session session = Hibernate.getSession()) {
            Transaction transaction = session.beginTransaction();
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);
            Root<User> root = criteriaQuery.from(User.class);
            criteriaQuery.where(criteriaBuilder.like(root.get("email"), email));
            User result = session.createQuery(criteriaQuery).getSingleResult();
            transaction.commit();
            return Optional.ofNullable(result);
        }
    }

    @Override
    public Optional<User> findByName(String name) {
        try (Session session = Hibernate.getSession()) {
            Transaction transaction = session.beginTransaction();
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<User> criteriaQuery = criteriaBuilder.createQuery(User.class);
            Root<User> root = criteriaQuery.from(User.class);
            criteriaQuery.where(criteriaBuilder.like(root.get("name"), name));
            User result = session.createQuery(criteriaQuery).getSingleResult();
            transaction.commit();
            return Optional.ofNullable(result);
        }
    }

}
