package com.oleksa.model.dao.impl;

import java.util.List;
import java.util.Optional;

import com.oleksa.model.dao.RecordDao;
import com.oleksa.model.dao.UserDao;
import com.oleksa.model.entity.Record;

public class RecordDaoImpl extends JdbcTemplate<Record> implements RecordDao {

    @Override
    public List<Record> findAllByClientId(long clientId) {
        UserDao userDao = new UserDaoImpl();
        return super.templateFindByForeignId(Record.class, "client", userDao.findById(clientId).get());
    }

    @Override
    public List<Record> findAllByClientIdWithMaster(long clientId) {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public List<Record> findAllWithComments() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public Record create(Record t) throws Exception {
        super.templateSave(t);
        return t;
    }

    @Override
    public void deleteById(Long id) {
        super.templateDeleteById("delete from Record where id = :id", id);
    }

    @Override
    public Record update(Record t) throws Exception {
        return super.templateUpdate(t);
    }

    @Override
    public Optional<Record> findById(Long id) {
        return Optional.ofNullable(super.templateFindById(Record.class, id));
    }

    @Override
    public List<Record> findAll() {
        return super.templateFindAll(Record.class, "from Record");
    }

}
