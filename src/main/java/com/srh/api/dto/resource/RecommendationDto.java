package com.srh.api.dto.resource;

import com.srh.api.model.Recommendation;
import lombok.Getter;
import org.springframework.data.domain.Page;
import org.springframework.hateoas.server.core.Relation;

import java.time.format.DateTimeFormatter;

@Getter
@Relation(collectionRelation = "recommendations")
public class RecommendationDto {
    private final Integer id;
    private final Double weight;
    private final String date;
    private final Integer runtimeInMiliSeconds;
    private final Integer algorithmId;
    private final Integer matrixId;

    public RecommendationDto(Recommendation recommendation) {
        this.id = recommendation.getId();
        this.weight = recommendation.getWeight();
        this.date = recommendation.getDate().format(DateTimeFormatter.ISO_DATE_TIME);
        this.runtimeInMiliSeconds = recommendation.getRuntimeInMiliSeconds();
        this.algorithmId = recommendation.getAlgorithm().getId();
        this.matrixId = recommendation.getMatrixId();
    }

    public static Page<RecommendationDto> convert(Page<Recommendation> recommendations) {
        return recommendations.map(RecommendationDto::new);
    }
}
