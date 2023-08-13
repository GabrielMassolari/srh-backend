package com.srh.api.hypermedia;

import com.srh.api.controller.AdminController;
import com.srh.api.controller.ProjectController;
import com.srh.api.controller.ProjectEvaluatorController;
import com.srh.api.dto.resource.ProjectDto;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
public class ProjectModelAssembler implements RepresentationModelAssembler<ProjectDto, EntityModel<ProjectDto>> {
    @Override
    public EntityModel<ProjectDto> toModel(ProjectDto projectDto) {
        return new EntityModel<>(projectDto,
                linkTo(methodOn(ProjectController.class).find(projectDto.getId())).withSelfRel(),
                linkTo(ProjectController.class).withRel("projects"),
                linkTo(methodOn(AdminController.class).find(projectDto.getAdmin().getId())).withRel("admin"),
                linkTo(methodOn(ProjectEvaluatorController.class).listRecommendersByProject(
                        projectDto.getId(), Pageable.unpaged())).withRel("evaluators"),
                linkTo(methodOn(ProjectController.class).findItensByProject(projectDto.getId(), Pageable.unpaged()))
                        .withRel("itens")
        );
    }
}
