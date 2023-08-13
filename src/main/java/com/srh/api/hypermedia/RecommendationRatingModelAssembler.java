package com.srh.api.hypermedia;

import com.srh.api.controller.EvaluatorController;
import com.srh.api.controller.RecommendationController;
import com.srh.api.controller.RecommendationRatingController;
import com.srh.api.dto.resource.RecommendationRatingDto;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class RecommendationRatingModelAssembler implements
        RepresentationModelAssembler<RecommendationRatingDto, EntityModel<RecommendationRatingDto>> {
    @Override
    public EntityModel<RecommendationRatingDto> toModel(RecommendationRatingDto recommendationRatingDto) {
        return new EntityModel<>(recommendationRatingDto,
                linkTo(methodOn(RecommendationRatingController.class).find(recommendationRatingDto.getId())).withSelfRel(),
                linkTo(methodOn(RecommendationRatingController.class).listAll(Pageable.unpaged())).withRel("recommendation ratings"),
                linkTo(methodOn((RecommendationController.class)).find(recommendationRatingDto.getRecommendation().getId())).withRel("recommendation"),
                linkTo(methodOn((EvaluatorController.class)).find(recommendationRatingDto.getEvaluator().getId())).withRel("evaluator")
        );
    }
}
