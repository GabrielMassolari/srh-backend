package com.srh.api.model;

import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
@Data
@EqualsAndHashCode(callSuper = true)
public class RecommendationRating extends Rating {
    @ManyToOne
    private Recommendation recommendation;

    @ManyToOne
    private Evaluator evaluator;
}
