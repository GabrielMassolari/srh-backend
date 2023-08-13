package com.srh.api.dto.resource;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.srh.api.model.Admin;
import com.srh.api.model.Project;
import lombok.Getter;
import org.springframework.data.domain.Page;
import org.springframework.hateoas.server.core.Relation;

import java.util.List;

@Getter
@Relation(collectionRelation = "admins")
public class AdminDto {
    private final Integer id;
    private final String name;
    private final String login;
    private final String email;
    @JsonIgnore
    private final List<Project> projects;

    public AdminDto(Admin admin) {
        this.id = admin.getId();
        this.name = admin.getName();
        this.login = admin.getLogin();
        this.email = admin.getEmail();
        this.projects = admin.getProjects();
    }

    public static Page<AdminDto> convert(Page<Admin> admins) {
        return admins.map(AdminDto::new);
    }
}
