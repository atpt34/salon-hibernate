package com.oleksa.model.entity;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Set;

import javax.persistence.*;

@Entity
@Table(name="record_t")
public class Record {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="rc_id")
    private Long id;
    
    @ManyToOne(cascade=CascadeType.REMOVE)
    @JoinColumn(name="rc_client_id", nullable=true)
    private User client;
    
    @Column(name="rc_time")
    private LocalTime hour;
    
    @Column(name="rc_day")
    private LocalDate day;
    
    @ManyToOne(cascade=CascadeType.REMOVE)
    @JoinColumn(name="rc_comment_id", nullable=true)
    private Comment comment;
    
    @ManyToMany(cascade=CascadeType.ALL, fetch=FetchType.LAZY)
    @JoinTable(name="schedule_record_m2m",
            joinColumns = {@JoinColumn(name = "sr_rc_id")},
            inverseJoinColumns = {@JoinColumn(name = "sr_sc_id")})
    private Set<Schedule> schedules;
    
}
