package com.srh.api.hypermedia;

import com.srh.api.controller.ProjectController;
import com.srh.api.controller.ProjectEvaluatorController;
import com.srh.api.controller.EvaluatorController;
import com.srh.api.dto.resource.ProjectEvaluatorDto;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class ProjectEvaluatorModelAssembler implements RepresentationModelAssembler<ProjectEvaluatorDto, EntityModel<ProjectEvaluatorDto>> {
    @Override
    public EntityModel<ProjectEvaluatorDto> toModel(ProjectEvaluatorDto projectRecommender) {
        return new EntityModel<>(projectRecommender,
                linkTo(methodOn(ProjectEvaluatorController.class).
                        findEvaluatorInProject(projectRecommender.getProjectId(),
                                projectRecommender.getEvaluatorId())).withSelfRel(),
                linkTo(methodOn(ProjectEvaluatorController.class).
                        listRecommendersByProject(projectRecommender.getProjectId(), Pageable.unpaged()))
                        .withRel("recommenders in project"),
                linkTo(methodOn(ProjectController.class).find(projectRecommender.getProjectId()))
                        .withRel("project"),
                linkTo(methodOn(EvaluatorController.class).find(projectRecommender.getEvaluatorId()))
                        .withRel("recommender")
        );
    }
}
