package com.oleksa.model.dao.impl;

import java.util.List;
import java.util.Optional;
import java.util.function.BiFunction;

import com.oleksa.model.dao.UserDao;
import com.oleksa.model.entity.User;
import com.oleksa.model.exception.NotUniqueEmailException;
import com.oleksa.model.exception.NotUniqueNameException;

public class UserDaoImpl extends JdbcTemplate<User> implements UserDao {

    private User commonCatch(BiFunction<UserDaoImpl, User, User> f, User t) throws NotUniqueNameException, NotUniqueEmailException {
        try {
            return f.apply(this, t);
        } catch (Exception e) {
            System.out.println("cause message: " + e.getCause().getMessage());
//            if (e.getCause().getMessage().matches("UK_\\(US_NAME\\)")) {
            if (e.getCause().getMessage().contains("US_NAME")) {
                throw new NotUniqueNameException();
            }
            if (e.getCause().getMessage().contains("US_EMAIL")) {
                throw new NotUniqueEmailException();
            }
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteById(Long id) {
        super.templateDeleteById("delete from User where id = :id", id);
    }

    @Override
    public User create(User t) throws NotUniqueEmailException, NotUniqueNameException {
        return commonCatch(UserDaoImpl::templateSave, t);
    }
    
    @Override
    public User update(User t) throws NotUniqueEmailException, NotUniqueNameException {
        return commonCatch(UserDaoImpl::templateUpdate, t);
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
