package com.srh.api.algorithms.strategies;

import com.srh.api.algorithms.math.CellPosition;
import com.srh.api.algorithms.resources.RecommendationAlgorithm;
import com.srh.api.algorithms.resources.utils.RecommendationsByEvaluator;
import com.srh.api.dto.resource.RecommendationForm;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class MixedHybridAsync implements RecommendationAlgorithm {
    @Autowired
    private CollaborativeAsync collaborative;

    @Autowired
    private ContentBasedAsync contentBased;

    private List<RecommendationsByEvaluator> recommendationsByEvaluators = new ArrayList<>();
    private List<CellPosition> recommendationsPositions = new ArrayList<>();

    @Override
    @SneakyThrows
    public List<RecommendationsByEvaluator> calc(RecommendationForm form) {
        List<List<RecommendationsByEvaluator>> listOfRecommendationsByEvaluators = new ArrayList<>();

        CompletableFuture<List<RecommendationsByEvaluator>> collaborativeFuture = CompletableFuture
                .supplyAsync(() -> collaborative.calc(form));

        CompletableFuture<List<RecommendationsByEvaluator>> contentFuture = CompletableFuture
                .supplyAsync(() -> contentBased.calc(form));

        CompletableFuture<List<RecommendationsByEvaluator>>[] hybridFuture =
                new CompletableFuture[] { collaborativeFuture, contentFuture };

        Arrays.stream(hybridFuture).map(CompletableFuture::join).forEach(
                listOfRecommendationsByEvaluators::add
        );

        recommendationsByEvaluators = joinResults(
                listOfRecommendationsByEvaluators.get(0),
                listOfRecommendationsByEvaluators.get(1)
        );

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
