package com.srh.api.algorithms.resources.utils;

import com.srh.api.model.Evaluator;
import com.srh.api.model.Recommendation;
import lombok.Data;

import java.util.List;

@Data
public class RecommendationsByEvaluator {
    private Evaluator evaluator;
    private List<Recommendation> recommendations;
    private Integer matrixId;
}
