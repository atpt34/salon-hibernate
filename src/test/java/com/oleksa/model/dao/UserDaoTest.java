package com.oleksa.model.dao;

import static org.junit.Assert.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.oleksa.model.dao.impl.UserDaoImpl;
import com.oleksa.model.entity.User;

public class UserDaoTest {

    private static UserDao userDao;
    
    private static User user1;
    private static User user2;
    private static User user3;
    private static User user4;
    private static User user5;
    
    @BeforeClass
    public static void setUp() {
        System.out.println( "Hello Hibernate World!" );
        userDao = new UserDaoImpl();
        try {
            user1 = new User(null, "name1", "password", "email1", "fullname", "role1");
            userDao.create(user1);
            user2 = new User(2L, "name2", "password", "email2", "fullname2", "role2");
            userDao.create(user2);
            user3 = new User(3L, "name3", "password", "email3", "fullname3", "role2");
            userDao.create(user3);
            user4 = new User(4L, "name4", "password", "email4", "fullname4", "role2");
            userDao.create(user4);
            user5 = new User(null, "name5", "password", "email5", "fullname4", "role3");
            userDao.create(user5);
        } catch (Exception e) {
            throw new RuntimeException();
        }
    }
    
    @Test
    public void testCreate() throws Exception {
        User user = new User(null, "oleksa", "password", "email", "fullname", "role");
        userDao.create(user);
        System.out.println(user);
        Long id = user.getId();
        assertNotNull(id);
        assertEquals(user, userDao.findById(id).get());
    }
    
    @Test
    public void testUpdate() throws Exception {
        String email = "somenewemail";
        user2.setEmail(email);
        userDao.update(user2);
        assertEquals(user2, userDao.findById(user2.getId()).get());
    }
    
    @Test
    public void testDeleteById() throws Exception {
        userDao.deleteById(user5.getId());
        assertEquals(Optional.empty(), userDao.findById(user5.getId()));
    }
    
    @Test
    public void testFindByName() throws Exception {
        Optional<User> findByName = userDao.findByName("name1");
        assertEquals(user1, findByName.get());
    }
    
    @Test
    public void testFindByEmail() throws Exception {
        Optional<User> findByName = userDao.findByEmail("email3");
        assertEquals(user3, findByName.get());
    }
    
    @Test
    public void testFindAll() throws Exception {
        List<User> list = userDao.findAll().stream().limit(4).collect(Collectors.toList());
        assertEquals(List.of(user1, user2, user3, user4), list);
    }
    
    @Test
    public void testUserFindById() throws Exception {
        Optional<User> actual = userDao.findById(1L);
        assertEquals(user1, actual.get());
    }
}
