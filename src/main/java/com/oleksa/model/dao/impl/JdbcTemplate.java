package com.oleksa.model.dao.impl;

import java.io.Serializable;
import java.util.List;
import java.util.function.BiFunction;

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.oleksa.model.entity.Idable;
import com.oleksa.model.util.Hibernate;

public class JdbcTemplate<T extends Idable> {
    
    public T templateSave(T t) {
        Serializable id = doInTransaction(Session::save, t);
        t.setId((long) id);
        return t;
    }
    
    public Serializable doInTransaction(BiFunction<Session, T, Serializable> func, T t) {
        try (Session session = Hibernate.getSession()) {
            Transaction transaction = session.beginTransaction();
            Serializable res = func.apply(session, t);
            transaction.commit();
            return res;
        }
    }


    public T templateUpdate(T t) {
        try (Session session = Hibernate.getSession()) {
            Transaction transaction = session.beginTransaction();
            session.update(t);
            transaction.commit();
            return t;
        }
    }
    
    public void templateDeleteById(String hql, Long id) {
        try (Session session = Hibernate.getSession()) {
            Transaction transaction = session.beginTransaction();
            Query query = session.createQuery(hql);
            query.setParameter("id", id);
            query.executeUpdate();
            transaction.commit();
        }
    }
    
    public List<T> templateFindAll(Class<T> clazz, String hql) {
        try (Session session = Hibernate.getSession()) {
            Transaction transaction = session.beginTransaction();
            org.hibernate.query.Query<T> query = session.createQuery(hql, clazz);
            List<T> result = query.getResultList();
            transaction.commit();
            return result;
        }
    }
    
    public T templateFindById(Class<T> clazz, Long id) {
        try (Session session = Hibernate.getSession()) {
            return session.find(clazz, id);
        } catch (IllegalArgumentException e) {
            return null;
        }
    }
    
    public T templateFindByStringField(Class<T> clazz, String like, String field) {
        try (Session session = Hibernate.getSession()) {
            Transaction transaction = session.beginTransaction();
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
            CriteriaQuery<T> criteriaQuery = criteriaBuilder.createQuery(clazz);
            Root<T> root = criteriaQuery.from(clazz);
            criteriaQuery.where(criteriaBuilder.like(root.get(field), like));
            T result = session.createQuery(criteriaQuery).getSingleResult();
            transaction.commit();
            return result;
        }
    }
}
