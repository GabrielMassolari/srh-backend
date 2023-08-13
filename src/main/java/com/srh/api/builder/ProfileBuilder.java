package com.srh.api.builder;

import com.srh.api.model.ApiUser;
import com.srh.api.model.Profile;

import java.util.List;

public final class ProfileBuilder {
    private Integer id;
    private String name;
    private List<ApiUser> apiUsers;

    private ProfileBuilder() {
    }

    public static ProfileBuilder aProfile() {
        return new ProfileBuilder();
    }

    public ProfileBuilder withId(Integer id) {
        this.id = id;
        return this;
    }

    public ProfileBuilder withName(String name) {
        this.name = name;
        return this;
    }

    public ProfileBuilder withApiUsers(List<ApiUser> apiUsers) {
        this.apiUsers = apiUsers;
        return this;
    }

    public Profile build() {
        Profile profile = new Profile();
        profile.setId(id);
        profile.setName(name);
        profile.setApiUsers(apiUsers);
        return profile;
    }
}
