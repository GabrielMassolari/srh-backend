package com.srh.api.algorithms.strategies;

import com.srh.api.algorithms.math.CellPosition;
import com.srh.api.algorithms.resources.RecommendationAlgorithm;
import com.srh.api.algorithms.resources.utils.RecommendationsByEvaluator;
import com.srh.api.dto.resource.RecommendationForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MixedHybrid implements RecommendationAlgorithm {
    @Autowired
    private Collaborative collaborative;

    @Autowired
    private ContentBased contentBased;

    private List<RecommendationsByEvaluator> recommendationsByEvaluators = new ArrayList<>();
    private List<CellPosition> recommendationsPositions = new ArrayList<>();

    @Override
    public List<RecommendationsByEvaluator> calc(RecommendationForm form) {
        List<RecommendationsByEvaluator> colllaborativeRecommendations = collaborative.calc(form);
        List<RecommendationsByEvaluator> contentBaseRecommendations = contentBased.calc(form);

        recommendationsByEvaluators = joinResults(colllaborativeRecommendations, contentBaseRecommendations);

        return recommendationsByEvaluators;
    }

    private List<RecommendationsByEvaluator> joinResults(
            List<RecommendationsByEvaluator> colllaborativeRecommendations,
            List<RecommendationsByEvaluator> contentBaseRecommendations
    ) {
        List<RecommendationsByEvaluator> results = new ArrayList<>();

        results.addAll(colllaborativeRecommendations);
        results.addAll(contentBaseRecommendations);

        return results;
    }

    @Override
    public List<CellPosition> getRecommendationsPositions() {
        return recommendationsPositions;
    }
}
