package com.oleksa.model.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Entity
@Table(name="comment_t")

@Data
@AllArgsConstructor
@RequiredArgsConstructor
public class Comment implements Idable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="cm_id")
    private Long id;
    
    @Column(name="cm_words")
    private String text;
    
    @Column(name="cm_stars")
    private int stars;

}
