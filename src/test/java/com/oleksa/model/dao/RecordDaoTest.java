package com.oleksa.model.dao;

import static org.junit.Assert.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import com.oleksa.model.dao.impl.CommentDaoImpl;
import com.oleksa.model.dao.impl.RecordDaoImpl;
import com.oleksa.model.dao.impl.ScheduleDaoImpl;
import com.oleksa.model.dao.impl.UserDaoImpl;
import com.oleksa.model.entity.Comment;
import com.oleksa.model.entity.Record;
import com.oleksa.model.entity.Schedule;
import com.oleksa.model.entity.User;

public class RecordDaoTest {

    private static RecordDao recordDao;
    private static UserDao userDao;
    private static CommentDao commentDao;
    private static ScheduleDao scheduleDao;
    
    private static Record record1;
    private static Record record2;
    private static Record record3;
    private static Record record4;
    private static Record record5;
    private static User client;
    private static User client2;
    private static User master;
    private static Comment comment;
    private static Schedule schedule;
    
    @BeforeClass
    public static void setUp() {
        recordDao = new RecordDaoImpl();
        client = new User(null, "name11", "password", "email11", "fullname", "role1");
        userDao = new UserDaoImpl();
        LocalTime hour = LocalTime.of(4, 30);
        LocalDate day = LocalDate.now();
        comment = new Comment(null, "hello", 5);
        master = new User(null, "name22", "password", "email22", "fullname", "role2");
        schedule = new Schedule(null, master,
                LocalDate.now(), LocalTime.NOON, LocalTime.MIDNIGHT, null);
        record1 = new Record(null, client, hour, day, comment, Set.of(schedule));
        System.out.println(record1);
        record2 = new Record(null, client, hour, day, null, null);
        record3 = new Record(null, client, hour, day, null, null);
        client2 = new User(null, "name111", "password", "email111", "fullname", "role1");
        record4 = new Record(null, client2, hour, day, null, null);
        record5 = new Record(null, client2, hour, day, null, null);
        System.out.println(record2);
        try {
            userDao.create(client);
            userDao.create(client2);
            userDao.create(master);
            commentDao = new CommentDaoImpl();
            commentDao.create(record1.getComment());
            scheduleDao = new ScheduleDaoImpl();
            scheduleDao.create(schedule);
            recordDao.create(record1);
            recordDao.create(record2);
            recordDao.create(record3);
            recordDao.create(record4);
            recordDao.create(record5);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    
    @AfterClass
    public static void tearDown() {
        System.out.println("tearDown");
        scheduleDao.deleteById(schedule.getId());
        recordDao.deleteById(record1.getId());
        recordDao.deleteById(record1.getId());
        recordDao.deleteById(record2.getId());
        recordDao.deleteById(record4.getId());
        recordDao.deleteById(record5.getId());
        userDao.deleteById(client.getId());
        userDao.deleteById(client2.getId());
        userDao.deleteById(master.getId());
        commentDao.deleteById(comment.getId());
    }
    
    @Test
    public void testDeleteById() throws Exception {
        recordDao.deleteById(record3.getId());
        assertEquals(Optional.empty(), recordDao.findById(record3.getId()));
    }
    
    @Test
    public void testFindById() throws Exception {
        Optional<Record> optional = recordDao.findById(record1.getId());
        System.out.println(optional.get());
        assertEquals(record1, optional.get());
    }
    
    @Test
    public void testFindAll() throws Exception {
        List<Record> actual = recordDao.findAll();
        assertEquals(client, actual.get(0).getClient());
        System.out.println(actual.get(0).getSchedules());
        assertEquals(record1, actual.get(0));
        System.out.println(actual);
        assertEquals(new ArrayList<>(List.of(record1, record2)), actual.stream().limit(2).collect(Collectors.toList()));
    }
    
    @Test
    public void testFindAllByClientId() {
        assertEquals(new ArrayList<>(List.of(record4, record5)), recordDao.findAllByClientId(client2.getId()));
    }
    
}
