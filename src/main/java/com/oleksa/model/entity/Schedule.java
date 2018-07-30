package com.oleksa.model.entity;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

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
public class Schedule implements Idable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="sc_id")
    private Long id;
    
    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name="sc_master_id", nullable=true)
    private User master;
    
    @Column(name="sc_day")
    private LocalDate day;
    
    @Column(name="sc_start_time")
    private LocalTime startHour;
    
    @Column(name="sc_end_time")
    private LocalTime endHour;
    
    @ManyToMany(mappedBy="schedules", fetch=FetchType.EAGER)
    private Set<Record> records;
    
//    private Set<LocalTime> freeHours;
    
    public Schedule() {
    }

    public Schedule(Long id, User master, LocalDate day, LocalTime startHour, LocalTime endHour, Set<Record> records) {
        this.id = id;
        this.master = master;
        this.day = day;
        this.startHour = startHour;
        this.endHour = endHour;
        this.records = records;
    }

    public Long getId() {
        return id;
    }


    public void setId(Long id) {
        this.id = id;
    }


    public User getMaster() {
        return master;
    }


    public void setMaster(User master) {
        this.master = master;
    }


    public LocalDate getDay() {
        return day;
    }


    public void setDay(LocalDate day) {
        this.day = day;
    }


    public LocalTime getStartHour() {
        return startHour;
    }


    public void setStartHour(LocalTime startHour) {
        this.startHour = startHour;
    }


    public LocalTime getEndHour() {
        return endHour;
    }


    public void setEndHour(LocalTime endHour) {
        this.endHour = endHour;
    }


    public Set<Record> getRecords() {
        return records;
    }


    public void setRecords(Set<Record> records) {
        this.records = records;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, master, day, startHour, endHour);//, records, freeHours);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Schedule that = (Schedule) obj;
        return Objects.equals(id, that.id)
                && Objects.equals(master, that.master)
                && Objects.equals(day, that.day)
                && Objects.equals(startHour, that.startHour)
                && Objects.equals(endHour, that.endHour);
//                && Objects.equals(records, that.records);
//                && Objects.equals(freeHours, that.freeHours);
    }

    @Override
    public String toString() {
        return "Schedule [id=" + id + ", master=" + master + ", day=" + day + ", startHour=" + startHour + ", endHour="
                + endHour + ", records=" + (records != null ? records.stream().map(s -> Objects.requireNonNullElse(s.getId(), "null").toString()).collect(Collectors.joining()) : null) + "]";
    }
    
}
