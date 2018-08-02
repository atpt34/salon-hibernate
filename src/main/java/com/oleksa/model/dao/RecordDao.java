package com.oleksa.model.dao;

import java.util.List;

import com.oleksa.model.entity.Record;

public interface RecordDao extends CrudDao<Record, Long> {

    List<Record> findAllByClientId(long clientId);

    List<Record> findAllByClientIdWithMaster(long clientId);

//    @Override
//    Record create(Record t) throws RecordOccupiedException;

    List<Record> findAllWithComments();
    
}
