package com.srh.api.dto.resource;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.srh.api.model.ApiUser;
import com.srh.api.model.Profile;
import lombok.Getter;
import org.springframework.data.domain.Page;
import org.springframework.hateoas.server.core.Relation;

import java.util.List;

@Getter
@Relation(collectionRelation = "apiUsers")
public class ApiUsersDto {
    private final Integer id;
    private final String name;
    private final String login;
    private final String email;
    private final Boolean isAdmin;

    @JsonIgnore
    private final List<Profile> profiles;

    public ApiUsersDto(ApiUser apiUser) {
        this.id = apiUser.getId();
        this.name = apiUser.getName();
        this.login = apiUser.getLogin();
        this.profiles = apiUser.getProfiles();
        this.email = apiUser.getEmail();
        this.isAdmin = apiUser.getIsAdmin();
    }

    public static Page<ApiUsersDto> convert(Page<ApiUser> users) {
        return users.map(ApiUsersDto::new);
    }
}
