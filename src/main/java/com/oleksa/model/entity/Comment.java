package com.oleksa.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

@Entity
@Table(name="comment_t")
@Data
public class Comment implements Idable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="cm_id")
    private Long id;
    
    @Column(name="cm_words")
    private String text;
    
    @Column(name="cm_stars")
    private int stars;

    public Comment() {
        // TODO Auto-generated constructor stub
    }
    
    public Comment(Long id, String text, int stars) {
        super();
        this.id = id;
        this.text = text;
        this.stars = stars;
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id  = id;
    }
}
