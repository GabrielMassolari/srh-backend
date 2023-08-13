package com.srh.api.dto.resource;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.srh.api.model.Evaluator;
import com.srh.api.model.Recommendation;
import com.srh.api.model.RecommendationRating;
import lombok.Getter;
import org.springframework.data.domain.Page;
import org.springframework.hateoas.server.core.Relation;

@Getter
@Relation(collectionRelation = "recommendationRatings")
public class RecommendationRatingDto extends RatingDto {
    @JsonIgnore
    private final Recommendation recommendation;
    @JsonIgnore
    private final Evaluator evaluator;

    public RecommendationRatingDto(RecommendationRating recommedationRating) {
        super(recommedationRating);
        this.recommendation = recommedationRating.getRecommendation();
        this.evaluator = recommedationRating.getEvaluator();
    }

    public static Page<RecommendationRatingDto> convert(Page<RecommendationRating> recommendationRatings) {
        return recommendationRatings.map(RecommendationRatingDto::new);
    }
}
