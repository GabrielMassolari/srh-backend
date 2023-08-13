package com.srh.api.builder;

import com.srh.api.model.Evaluator;
import com.srh.api.model.Recommendation;
import com.srh.api.model.RecommendationRating;

import java.time.LocalDateTime;

public final class RecommendationRatingBuilder {
    private Recommendation recommendation;
    private Evaluator evaluator;
    private Integer id;
    private Double score;
    private LocalDateTime date;

    private RecommendationRatingBuilder() {
    }

    public static RecommendationRatingBuilder aRecommendationRating() {
        return new RecommendationRatingBuilder();
    }

    public RecommendationRatingBuilder withRecommendation(Recommendation recommendation) {
        this.recommendation = recommendation;
        return this;
    }

    public RecommendationRatingBuilder withEvaluator(Evaluator evaluator) {
        this.evaluator = evaluator;
        return this;
    }

    public RecommendationRatingBuilder withId(Integer id) {
        this.id = id;
        return this;
    }

    public RecommendationRatingBuilder withScore(Double score) {
        this.score = score;
        return this;
    }

    public RecommendationRatingBuilder withDate(LocalDateTime date) {
        this.date = date;
        return this;
    }

    public RecommendationRating build() {
        RecommendationRating recommendationRating = new RecommendationRating();
        recommendationRating.setRecommendation(recommendation);
        recommendationRating.setEvaluator(evaluator);
        recommendationRating.setId(id);
        recommendationRating.setScore(score);
        recommendationRating.setDate(date);
        return recommendationRating;
    }
}
