package com.oleksa.model.entity;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="schedule_t")
public class Schedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="sc_id")
    private Long id;
    
//    @Column(name="sc_master_id")
    @ManyToOne(cascade=CascadeType.REMOVE)
    @JoinColumn(name="sc_master_id", nullable=true)
    private User master;
    
    @Column(name="sc_day")
    private LocalDate day;
    
    @Column(name="sc_start_time")
    private LocalTime startHour;
    
    @Column(name="sc_end_time")
    private LocalTime endHour;
    
    @ManyToMany(mappedBy="schedules", fetch=FetchType.LAZY)
    private Set<Record> records;
    
//    private Set<LocalTime> freeHours;
    
}
