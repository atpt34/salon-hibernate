package com.oleksa.model.entity;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.*;

import lombok.*;

@Entity
@Table(name="record_t")

@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
public class Record implements Idable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="rc_id")
    private Long id;
    
    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name="rc_client_id", nullable=true)
    private User client;
    
    @Column(name="rc_time")
    private LocalTime hour;
    
    @Column(name="rc_day")
    private LocalDate day;
    
    @ManyToOne(cascade=CascadeType.ALL, fetch=FetchType.EAGER)
    @JoinColumn(name="rc_comment_id", nullable=true)
    private Comment comment;
    
    @ManyToMany(cascade=CascadeType.ALL, fetch=FetchType.EAGER)
    @JoinTable(name="schedule_record_m2m",
            joinColumns = {@JoinColumn(name = "sr_rc_id")},
            inverseJoinColumns = {@JoinColumn(name = "sr_sc_id")})
    private Set<Schedule> schedules;
    
    @Override
    public int hashCode() {
        return Objects.hash(id, client, hour, day, comment);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Record that = (Record) obj;
        return Objects.equals(id, that.id)
                && Objects.equals(client, that.client)
                && Objects.equals(hour, that.hour)
                && Objects.equals(day, that.day)
                && Objects.equals(comment, that.comment);
    }

    @Override
    public String toString() {
        return "Record [id=" + id + ", client=" + client + ", hour=" + hour + ", day=" + day + ", comment=" + comment
                + ", schedules=" + (getSchedules() != null ? schedules.stream().map(s -> Objects.requireNonNullElse(s.getId(), "null").toString()).collect(Collectors.joining()) : null) + "]";
    }
    
    
}
