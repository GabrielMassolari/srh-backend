package com.srh.api.hypermedia;

import com.srh.api.controller.AdminController;
import com.srh.api.controller.ProjectController;
import com.srh.api.dto.resource.AdminDto;
import com.srh.api.model.Project;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@Component
public class AdminModelAssembler implements RepresentationModelAssembler<AdminDto, EntityModel<AdminDto>> {
    @Override
    public EntityModel<AdminDto> toModel(AdminDto adminDto) {
        EntityModel<AdminDto> adminEntityModel = new EntityModel<>(adminDto,
                linkTo(methodOn(AdminController.class).find(adminDto.getId())).withSelfRel(),
                linkTo(AdminController.class).withRel("admins")
        );

        if (adminDto.getProjects() != null) {
            adminEntityModel.add(buildProjectsLinks(adminDto.getProjects()));
        }

        return adminEntityModel;
    }

    private List<Link> buildProjectsLinks(List<Project> projects) {
        List<Link> links = new ArrayList<>();

        for (Project project : projects) {
            links.add(linkTo(methodOn(ProjectController.class).find(project.getId())).withRel("projects"));
        }

        return links;
    }

}
