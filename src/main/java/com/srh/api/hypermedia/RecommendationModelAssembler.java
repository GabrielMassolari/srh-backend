package com.srh.api.hypermedia;

import com.srh.api.controller.RecommendationController;
import com.srh.api.controller.EvaluatorController;
import com.srh.api.dto.resource.RecommendationDto;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class RecommendationModelAssembler implements RepresentationModelAssembler<RecommendationDto, EntityModel<RecommendationDto>> {
    @Override
    public EntityModel<RecommendationDto> toModel(RecommendationDto recommendationDto) {
        return new EntityModel<>(recommendationDto,
                linkTo(methodOn(RecommendationController.class).find(recommendationDto.getId())).withSelfRel(),
                linkTo(RecommendationController.class).withRel("recommendations")
        );
    }
}
