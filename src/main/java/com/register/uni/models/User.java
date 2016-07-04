package com.register.uni.models;


import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name="user")
public class User {
    public enum Role {STUDENT, INSTITUTION, ADMIN}

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false, updatable = false)
    private Long id;

    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "password_hash", nullable = false)
    private String password;

    @Column(name = "role", nullable = false)
    @Enumerated(EnumType.STRING)
    private Role role;
}
