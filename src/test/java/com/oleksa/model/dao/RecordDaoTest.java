package com.oleksa.model.dao;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.junit.BeforeClass;
import org.junit.Test;

import com.oleksa.model.dao.impl.RecordDaoImpl;
import com.oleksa.model.dao.impl.UserDaoImpl;
import com.oleksa.model.entity.Comment;
import com.oleksa.model.entity.Record;
import com.oleksa.model.entity.Schedule;
import com.oleksa.model.entity.User;

public class RecordDaoTest {

    private static RecordDao recordDao;
    
    private static Record record1;
    
    @BeforeClass
    public static void setUp() {
        recordDao = new RecordDaoImpl();
        Long id = null;
        User client = new User(null, "name11", "password", "email11", "fullname", "role1");
        UserDao userDao = new UserDaoImpl();
        try {
            userDao.create(client);
        } catch (Exception e1) {
            throw new RuntimeException(e1);
        }
        LocalTime hour = LocalTime.of(4, 30);
        LocalDate day = LocalDate.now();
        Comment comment = null; //new Comment();
        Set<Schedule> schedules = null; //Set.of(new Schedule());
        record1 = new Record(id, client, hour, day, comment, schedules);
        System.out.println(record1);
        try {
            record1 = recordDao.create(record1);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    
    @Test
    public void testFindAll() throws Exception {
        List<Record> actual = recordDao.findAll();
        System.out.println(actual);
        assertEquals(new ArrayList<>(List.of(record1)), actual);
    }
}
