package com.srh.api.builder;

import com.srh.api.model.Admin;
import com.srh.api.model.Project;

import java.util.List;

public final class AdminBuilder {
    protected String login;
    protected String password;
    private List<Project> projects;
    private Integer id;
    private String oldPassword;
    private String name;
    private String email;

    private AdminBuilder() {
    }

    public static AdminBuilder anAdmin() {
        return new AdminBuilder();
    }

    public AdminBuilder withProjects(List<Project> projects) {
        this.projects = projects;
        return this;
    }

    public AdminBuilder withId(Integer id) {
        this.id = id;
        return this;
    }

    public AdminBuilder withLogin(String login) {
        this.login = login;
        return this;
    }

    public AdminBuilder withOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
        return this;
    }

    public AdminBuilder withName(String name) {
        this.name = name;
        return this;
    }

    public AdminBuilder withEmail(String email) {
        this.email = email;
        return this;
    }

    public AdminBuilder withPassword(String password) {
        this.password = password;
        return this;
    }

    public Admin build() {
        Admin admin = new Admin();
        admin.setProjects(projects);
        admin.setId(id);
        admin.setLogin(login);
        admin.setOldPassword(oldPassword);
        admin.setName(name);
        admin.setEmail(email);
        admin.setPassword(password);
        return admin;
    }
}
