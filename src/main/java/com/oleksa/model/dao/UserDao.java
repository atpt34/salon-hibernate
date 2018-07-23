package com.oleksa.model.dao;

import java.util.Optional;

import com.oleksa.model.entity.User;

public interface UserDao extends CrudDao<User, Long> {

//    @Override
//    User create(User t) throws NotUniqueNameException, NotUniqueEmailException;
//
//    @Override
//    User update(User t) throws NotUniqueNameException, NotUniqueEmailException;

    Optional<User> findByEmail(String email);

    Optional<User> findByName(String name);
    
}
