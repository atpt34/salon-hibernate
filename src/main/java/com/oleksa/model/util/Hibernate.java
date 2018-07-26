package com.oleksa.model.util;

import java.util.Properties;

import org.h2.Driver;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.dialect.H2Dialect;

import com.oleksa.model.entity.Comment;
import com.oleksa.model.entity.Record;
import com.oleksa.model.entity.Schedule;
import com.oleksa.model.entity.User;

public class Hibernate {
    
    private static final SessionFactory SESSION_FACTORY = initialize();
    private static final String DATABASE_CONNECTION_STRING = "jdbc:h2:mem:test;DB_CLOSE_DELAY=-1;MODE=PostgreSQL";

    public static Session getSession() {
        return SESSION_FACTORY.openSession();
    }
    
    public static void closeFactory() {
        SESSION_FACTORY.close();
    }

    private static SessionFactory initialize() {
        Properties jpaProps = new Properties();
        jpaProps.put(Environment.DRIVER, Driver.class.getCanonicalName());
        jpaProps.put(Environment.DIALECT, H2Dialect.class.getCanonicalName());
        jpaProps.put(Environment.URL, DATABASE_CONNECTION_STRING);
        jpaProps.put(Environment.USER, "user");
        jpaProps.put(Environment.PASS, "password");
        jpaProps.put(Environment.HBM2DDL_AUTO, "create-drop");
        jpaProps.put(Environment.SHOW_SQL, true);

        Configuration config = new Configuration()
                .addAnnotatedClass(User.class)
                .addAnnotatedClass(Comment.class)
                .addAnnotatedClass(Record.class)
                .addAnnotatedClass(Schedule.class)
                .addProperties(jpaProps);

        return config.buildSessionFactory();
    }
    
}
