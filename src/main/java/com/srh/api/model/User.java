package com.srh.api.model;

import lombok.Data;

import javax.persistence.*;

@MappedSuperclass
@Data
public abstract class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique = true)
    protected String login;

    @Transient
    private String oldPassword;

    private String name;
    private String email;
    protected String password;
}
