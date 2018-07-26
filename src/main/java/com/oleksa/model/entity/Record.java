package com.oleksa.model.entity;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Set;

import javax.persistence.*;

import lombok.Data;

@Entity
@Table(name="record_t")
@Data
public class Record implements Idable {

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

    public Record() { }

    public Record(Long id, User client, LocalTime hour, LocalDate day, Comment comment, Set<Schedule> schedules) {
        this.id = id;
        this.client = client;
        this.hour = hour;
        this.day = day;
        this.comment = comment;
        this.schedules = schedules;
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }
    
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((client == null) ? 0 : client.hashCode());
        result = prime * result + ((comment == null) ? 0 : comment.hashCode());
        result = prime * result + ((day == null) ? 0 : day.hashCode());
        result = prime * result + ((hour == null) ? 0 : hour.hashCode());
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((schedules == null) ? 0 : schedules.hashCode());
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
        Record other = (Record) obj;
        if (client == null) {
            if (other.client != null)
                return false;
        } else if (!client.equals(other.client))
            return false;
        if (comment == null) {
            if (other.comment != null)
                return false;
        } else if (!comment.equals(other.comment))
            return false;
        if (day == null) {
            if (other.day != null)
                return false;
        } else if (!day.equals(other.day))
            return false;
        if (hour == null) {
            if (other.hour != null)
                return false;
        } else if (!hour.equals(other.hour))
            return false;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (schedules == null) {
            if (other.schedules != null)
                return false;
        } else if (!schedules.equals(other.schedules))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Record [id=" + id + ", client=" + client + ", hour=" + hour + ", day=" + day + ", comment=" + comment + "]";
               // + ", schedules=" + schedules + "]";
    }
    
    
}
