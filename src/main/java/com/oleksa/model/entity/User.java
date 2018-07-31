package com.oleksa.model.entity;

import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@EqualsAndHashCode(callSuper=false)
@ToString
@RequiredArgsConstructor
@AllArgsConstructor
@Builder(toBuilder=true)
@Getter
@Setter

@Entity
@Table(name="user_t")
public class User extends AbstractEntity implements Idable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="us_id")
    private Long id;
    
    @Column(name="us_name", nullable=false, unique=true)
    private String name;
    
    @Column(name="us_password", nullable=false)
    private String password;
    
    @Column(name="us_email", nullable=false, unique=true)
    private String email;
    
    @Column(name="us_fullname")
    private String fullname;
    
    @Column(name="us_role", nullable=false)
    private String role;
    
}
