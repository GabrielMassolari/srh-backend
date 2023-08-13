package com.srh.api.builder;

import com.srh.api.model.*;

import java.util.List;

public final class EvaluatorBuilder {
    protected String login;
    protected String password;
    private List<Project> projects;
    private List<Recommendation> recommendations;
    private List<ItemRating> itemRatings;
    private List<RecommendationRating> recommendationRatings;
    private Integer id;
    private String oldPassword;
    private String name;
    private String email;

    private EvaluatorBuilder() {
    }

    public static EvaluatorBuilder anEvaluator() {
        return new EvaluatorBuilder();
    }

    public EvaluatorBuilder withProjects(List<Project> projects) {
        this.projects = projects;
        return this;
    }

    public EvaluatorBuilder withRecommendations(List<Recommendation> recommendations) {
        this.recommendations = recommendations;
        return this;
    }

    public EvaluatorBuilder withItemRatings(List<ItemRating> itemRatings) {
        this.itemRatings = itemRatings;
        return this;
    }

    public EvaluatorBuilder withRecommendationRatings(List<RecommendationRating> recommendationRatings) {
        this.recommendationRatings = recommendationRatings;
        return this;
    }

    public EvaluatorBuilder withId(Integer id) {
        this.id = id;
        return this;
    }

    public EvaluatorBuilder withLogin(String login) {
        this.login = login;
        return this;
    }

    public EvaluatorBuilder withOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
        return this;
    }

    public EvaluatorBuilder withName(String name) {
        this.name = name;
        return this;
    }

    public EvaluatorBuilder withEmail(String email) {
        this.email = email;
        return this;
    }

    public EvaluatorBuilder withPassword(String password) {
        this.password = password;
        return this;
    }

    public Evaluator build() {
        Evaluator evaluator = new Evaluator();
        evaluator.setProjects(projects);
        evaluator.setRecommendations(recommendations);
        evaluator.setItemRatings(itemRatings);
        evaluator.setRecommendationRatings(recommendationRatings);
        evaluator.setId(id);
        evaluator.setLogin(login);
        evaluator.setOldPassword(oldPassword);
        evaluator.setName(name);
        evaluator.setEmail(email);
        evaluator.setPassword(password);
        return evaluator;
    }
}
