package com.srh.api.builder;

import com.srh.api.model.Algorithm;
import com.srh.api.model.Recommendation;
import com.srh.api.model.TypeRecommendation;

import java.util.List;

public final class AlgorithmBuilder {
    private Integer id;
    private String name;
    private TypeRecommendation typeRecommendation;
    private List<Recommendation> recommendations;

    private AlgorithmBuilder() {
    }

    public static AlgorithmBuilder anAlgorithm() {
        return new AlgorithmBuilder();
    }

    public AlgorithmBuilder withId(Integer id) {
        this.id = id;
        return this;
    }

    public AlgorithmBuilder withName(String name) {
        this.name = name;
        return this;
    }

    public AlgorithmBuilder withTypeRecommendation(TypeRecommendation typeRecommendation) {
        this.typeRecommendation = typeRecommendation;
        return this;
    }

    public AlgorithmBuilder withRecommendations(List<Recommendation> recommendations) {
        this.recommendations = recommendations;
        return this;
    }

    public Algorithm build() {
        Algorithm algorithm = new Algorithm();
        algorithm.setId(id);
        algorithm.setName(name);
        algorithm.setTypeRecommendation(typeRecommendation);
        algorithm.setRecommendations(recommendations);
        return algorithm;
    }
}
