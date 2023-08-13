package com.srh.api.builder;

import com.srh.api.model.ApiUser;
import com.srh.api.model.Profile;

import java.util.List;

public final class ApiUserBuilder {
    protected String login;
    protected String password;
    private List<Profile> profiles;
    private Integer id;
    private String oldPassword;
    private String name;
    private String email;
    private Boolean isAdmin;

    private ApiUserBuilder() {
    }

    public static ApiUserBuilder anApiUser() {
        return new ApiUserBuilder();
    }

    public ApiUserBuilder withProfiles(List<Profile> profiles) {
        this.profiles = profiles;
        return this;
    }

    public ApiUserBuilder withId(Integer id) {
        this.id = id;
        return this;
    }

    public ApiUserBuilder withLogin(String login) {
        this.login = login;
        return this;
    }

    public ApiUserBuilder withOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
        return this;
    }

    public ApiUserBuilder withName(String name) {
        this.name = name;
        return this;
    }

    public ApiUserBuilder withEmail(String email) {
        this.email = email;
        return this;
    }

    public ApiUserBuilder withPassword(String password) {
        this.password = password;
        return this;
    }

    public ApiUserBuilder withIsAdmin(Boolean isAdmin) {
        this.isAdmin = isAdmin;
        return this;
    }

    public ApiUser build() {
        ApiUser apiUser = new ApiUser();
        apiUser.setProfiles(profiles);
        apiUser.setId(id);
        apiUser.setLogin(login);
        apiUser.setOldPassword(oldPassword);
        apiUser.setName(name);
        apiUser.setEmail(email);
        apiUser.setPassword(password);
        apiUser.setIsAdmin(isAdmin);
        return apiUser;
    }
}
