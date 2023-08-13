package com.srh.api.dto.resource;

import com.srh.api.algorithms.resources.utils.RecommendationsByEvaluator;
import com.srh.api.model.Recommendation;
import lombok.Getter;
import org.springframework.data.domain.Page;
import org.springframework.hateoas.server.core.Relation;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Relation(collectionRelation = "recommendations")
public class RecommendationsByEvaluatorDto {
    private final Integer evaluatorId;
    private final Integer matrixId;
    private final List<RecommendationDto> recommendations;

    public RecommendationsByEvaluatorDto(RecommendationsByEvaluator recommendationsByEvaluator) {
        this.evaluatorId = recommendationsByEvaluator.getEvaluator().getId();
        this.matrixId = recommendationsByEvaluator.getMatrixId();
        this.recommendations = convertRecommendations(recommendationsByEvaluator.getRecommendations());
    }

    public static Page<RecommendationsByEvaluatorDto> convert(
            Page<RecommendationsByEvaluator> recommendationsByEvaluators) {
        return recommendationsByEvaluators.map(RecommendationsByEvaluatorDto::new);
    }

    public static List<RecommendationDto> convertRecommendations(List<Recommendation> recommendations) {
        return recommendations.stream().map(RecommendationDto::new).collect(Collectors.toList());
    }
}
