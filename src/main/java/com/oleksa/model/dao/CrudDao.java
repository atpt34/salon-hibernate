package com.oleksa.model.dao;

import java.util.List;
import java.util.Optional;

public interface CrudDao<T, Id> {

    T create(T t) throws Exception;

    void deleteById(Id id);

    T update(T t) throws Exception;

    Optional<T> findById(Id id);

    List<T> findAll();
}
