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

public class UserDaoImpl extends JdbcTemplate<User> implements UserDao {

    public User create(User t) throws Exception {
        super.templateSave(t);
        return t;
    }

    @Override
    public void deleteById(Long id) {
        super.templateDeleteById("delete from User where id = :id", id);
    }

    @Override
    public User update(User t) throws Exception {
        return super.templateUpdate(t);
    }

    @Override
    public Optional<User> findById(Long id) {
        return Optional.ofNullable(super.templateFindById(User.class, id));
    }

    @Override
    public List<User> findAll() {
        return super.templateFindAll(User.class, "from User");
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return Optional.ofNullable(super.templateFindByStringField(User.class, email, "email"));
    }

    @Override
    public Optional<User> findByName(String name) {
        return Optional.ofNullable(super.templateFindByStringField(User.class, name, "name"));
    }

}
