package com.srh.api.algorithms.strategies;

import com.srh.api.algorithms.math.CellPosition;
import com.srh.api.algorithms.resources.RecommendationAlgorithm;
import com.srh.api.algorithms.resources.utils.RecommendationUtils;
import com.srh.api.algorithms.resources.utils.RecommendationsByEvaluator;
import com.srh.api.builder.AlgorithmBuilder;
import com.srh.api.builder.RecommendationBuilder;
import com.srh.api.dto.resource.RecommendationForm;
import com.srh.api.model.Algorithm;
import com.srh.api.model.Evaluator;
import com.srh.api.model.Recommendation;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Stream;

@Service
public class WeightedHybridAsync implements RecommendationAlgorithm {
    @Autowired
    private Collaborative collaborative;

    @Autowired
    private ContentBased contentBased;

    @Autowired
    private RecommendationUtils recommendationUtils;

    private LocalDateTime startRecommendationTime;
    private List<RecommendationsByEvaluator> recommendationsByEvaluators = new ArrayList<>();
    private Double passingScore;
    private LocalDateTime startTime;
    private Integer projectId;
    private Integer decimalPrecision;

    @Override
    @SneakyThrows
    public List<RecommendationsByEvaluator> calc(RecommendationForm form) {
        startTime = LocalDateTime.now();
        passingScore = form.getPassingScore();
        projectId = form.getProjectId();
        decimalPrecision = form.getDecimalPrecision();
        startRecommendationTime = LocalDateTime.now();
        form.setPassingScore(0.0);

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

        for (RecommendationsByEvaluator collaborativeRecommendationEvaluator : colllaborativeRecommendations) {
            RecommendationsByEvaluator recommendationsByEvaluator = new RecommendationsByEvaluator();
            recommendationsByEvaluator.setEvaluator(collaborativeRecommendationEvaluator.getEvaluator());

            List<Recommendation> collaborativeRecommendations = collaborativeRecommendationEvaluator.getRecommendations();
            List<Recommendation> contentRecommendations = getRecommendationsByEvaluator(
                    collaborativeRecommendationEvaluator.getEvaluator(), contentBaseRecommendations
            );

            List<Recommendation> weightedRecommendations = buildWeightedRecommendation(collaborativeRecommendations, contentRecommendations);

            recommendationsByEvaluator.setRecommendations(registerRecommendations(weightedRecommendations));
            recommendationsByEvaluator.setMatrixId(recommendationUtils.getNewMatrixIndexByProjectId(projectId));

            results.add(recommendationsByEvaluator);
        }

        recommendationUtils.defineNewMatrixId(projectId);
        return results;
    }

    private List<Recommendation> registerRecommendations(List<Recommendation> recommendations) {
        return recommendationUtils.saveRecommendationList(recommendations, startRecommendationTime, 3);
    }

    private List<Recommendation> buildWeightedRecommendation(List<Recommendation> collaborativeRecommendations,
                                                             List<Recommendation> contentRecommendations) {
        List<Recommendation> results = new ArrayList<>();

        for (int i = 0; i < collaborativeRecommendations.size(); i++) {
            Recommendation averageRecommendation = calculateRecommendation(
                    collaborativeRecommendations.get(i), contentRecommendations.get(i)
            );

            if (averageRecommendation.getWeight() >= passingScore) {
                results.add(averageRecommendation);
            }
        }

        return results;
    }

    private Recommendation calculateRecommendation(Recommendation collaborativeRecommendation,
                                                   Recommendation contentRecommendation) {
        Double sumWeights = collaborativeRecommendation.getWeight() + contentRecommendation.getWeight();
        Double average = sumWeights / 2;

        return buildRecommendation(collaborativeRecommendation, average);
    }

    private Recommendation buildRecommendation(Recommendation baseRecommendation, Double score) {
        return RecommendationBuilder.aRecommendation()
                .withEvaluator(baseRecommendation.getEvaluator())
                .withItem(baseRecommendation.getItem())
                .withRecommendationRatings(baseRecommendation.getRecommendationRatings())
                .withRuntimeInSeconds(recommendationUtils.calculateDifferenceTime(startTime, LocalDateTime.now()))
                .withWeight(RecommendationUtils.roundValue(score, decimalPrecision))
                .withDate(LocalDateTime.now())
                .withMatrixId(recommendationUtils.getNewMatrixIndexByProjectId(projectId))
                .withAlgorithm(getAlgorithm())
                .build();
    }

    private Algorithm getAlgorithm() {
        return AlgorithmBuilder.anAlgorithm()
                .withId(3)
                .build();
    }

    private List<Recommendation> getRecommendationsByEvaluator(Evaluator evaluator, List<RecommendationsByEvaluator> recommendationsByEvaluators) {
        Stream<RecommendationsByEvaluator> recommendationsByEvaluatorStream = recommendationsByEvaluators.stream()
                .filter(recommendationsByEvaluator1 -> recommendationsByEvaluator1.getEvaluator().equals(evaluator));

        Optional<RecommendationsByEvaluator> first = recommendationsByEvaluatorStream.findFirst();

        if (first.isPresent())
            return first.get().getRecommendations();

        return new ArrayList<>();
    }

    @Override
    public List<CellPosition> getRecommendationsPositions() {
        return null;
    }
}
