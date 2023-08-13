package com.srh.api.hypermedia;

import com.srh.api.dto.resource.RecommendationsByEvaluatorDto;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

@Component
public class RecommendationsByEvaluatorModelAssembler
        implements RepresentationModelAssembler<RecommendationsByEvaluatorDto, EntityModel<RecommendationsByEvaluatorDto>> {

    public EntityModel<RecommendationsByEvaluatorDto> toModel(RecommendationsByEvaluatorDto recommendationsByEvaluatorDto) {
        return new EntityModel<>(recommendationsByEvaluatorDto);
    }
}
