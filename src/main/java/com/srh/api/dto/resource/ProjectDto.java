package com.srh.api.dto.resource;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.srh.api.model.Admin;
import com.srh.api.model.Project;
import com.srh.api.model.Situations;
import lombok.Getter;
import org.springframework.data.domain.Page;
import org.springframework.hateoas.server.core.Relation;

import java.time.LocalDate;

@Getter
@Relation(collectionRelation = "projects")
public class ProjectDto {
    private final Integer id;
    private final String name;
    private final String description;
    private final Situations situation;
    private final LocalDate date;
    private final Boolean visible;
    private final Integer lastMatrixId;
    private final Integer adminId;

    @JsonIgnore
    private final Admin admin;

    public ProjectDto(Project project) {
        this.id = project.getId();
        this.name = project.getName();
        this.description = project.getDescription();
        this.date = project.getDate();
        this.situation = project.getSituation();
        this.visible = project.getVisible();
        this.admin = project.getAdmin();
        this.lastMatrixId = project.getLastMatrixId();
        this.adminId = project.getAdmin().getId();
    }

    public static Page<ProjectDto> convert(Page<Project> projects) {
        return projects.map(ProjectDto::new);
    }
}
