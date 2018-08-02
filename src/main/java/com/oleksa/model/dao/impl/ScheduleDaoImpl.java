package com.oleksa.model.dao.impl;

import java.util.List;
import java.util.Optional;

import com.oleksa.model.dao.ScheduleDao;
import com.oleksa.model.entity.Schedule;

public class ScheduleDaoImpl extends JdbcTemplate<Schedule> implements ScheduleDao {

    @Override
    public Schedule create(Schedule t) throws Exception {
        return super.templateSave(t); 
    }

    @Override
    public void deleteById(Long id) {
        super.templateDeleteById("delete from Schedule where id = :id", id);
    }

    @Override
    public Schedule update(Schedule t) throws Exception {
        return super.templateUpdate(t);
    }

    @Override
    public Optional<Schedule> findById(Long id) {
        return Optional.ofNullable(super.templateFindById(Schedule.class, id));
    }

    @Override
    public List<Schedule> findAll() {
        return super.templateFindAll(Schedule.class, "from Schedule");
    }

}
