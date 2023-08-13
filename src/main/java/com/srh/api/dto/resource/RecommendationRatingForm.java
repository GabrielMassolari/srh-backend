package com.srh.api.dto.resource;

import com.srh.api.builder.EvaluatorBuilder;
import com.srh.api.builder.RecommendationBuilder;
import com.srh.api.builder.RecommendationRatingBuilder;
import com.srh.api.model.Evaluator;
import com.srh.api.model.Recommendation;
import com.srh.api.model.RecommendationRating;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class RecommendationRatingForm {
    @NotNull
    private Double score;
    @NotNull
    private Integer evaluatorId;
    @NotNull
    private Integer recommendationId;

    public RecommendationRating build() {
        Evaluator evaluator = EvaluatorBuilder.anEvaluator()
                .withId(evaluatorId)
                .build();

        Recommendation recommendation = RecommendationBuilder.aRecommendation()
                .withId(recommendationId)
                .build();

        return RecommendationRatingBuilder.aRecommendationRating()
                .withScore(score)
                .withEvaluator(evaluator)
                .withRecommendation(recommendation)
                .withScore(score)
                .withDate(LocalDateTime.now())
                .build();
    }
}
