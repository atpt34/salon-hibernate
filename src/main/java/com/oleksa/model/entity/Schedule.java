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
        final int prime = 31;
        int result = 1;
        result = prime * result + ((day == null) ? 0 : day.hashCode());
        result = prime * result + ((endHour == null) ? 0 : endHour.hashCode());
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((master == null) ? 0 : master.hashCode());
        result = prime * result + ((records == null) ? 0 : records.hashCode());
        result = prime * result + ((startHour == null) ? 0 : startHour.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Schedule other = (Schedule) obj;
        if (day == null) {
            if (other.day != null)
                return false;
        } else if (!day.equals(other.day))
            return false;
        if (endHour == null) {
            if (other.endHour != null)
                return false;
        } else if (!endHour.equals(other.endHour))
            return false;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (master == null) {
            if (other.master != null)
                return false;
        } else if (!master.equals(other.master))
            return false;
        if (records == null) {
            if (other.records != null)
                return false;
        } else if (!records.equals(other.records))
            return false;
        if (startHour == null) {
            if (other.startHour != null)
                return false;
        } else if (!startHour.equals(other.startHour))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Schedule [id=" + id + ", master=" + master + ", day=" + day + ", startHour=" + startHour + ", endHour="
                + endHour + ", records=" + records + "]";
    }
    
}
