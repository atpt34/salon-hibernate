package com.oleksa.model.entity;

import javax.persistence.*;

@Entity
@Table(name="role_t")
public enum UserRole {
    ADMIN, MANAGER, USER;
}
