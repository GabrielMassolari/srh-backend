package com.srh.api.algorithms.resources.utils;

import com.srh.api.algorithms.math.CellPosition;
import com.srh.api.algorithms.math.Coordinate;
import com.srh.api.builder.RecommendationBuilder;
import com.srh.api.model.*;
import com.srh.api.service.AlgorithmService;
import com.srh.api.service.ProjectService;
import com.srh.api.service.RecommendationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

@Component
public class RecommendationUtils {
    @Autowired
    private ProjectService projectService;

    @Autowired
    private RecommendationService recommendationService;

    @Autowired
    private AlgorithmService algorithmService;

    public Recommendation buildRecommendation(Double score, LocalDateTime startRecommendationTime, Integer algorithmId,
                                              Item item, Evaluator evaluator, Project project) {
        LocalDateTime endRecommendationTime = LocalDateTime.now();

        Recommendation recommendation = RecommendationBuilder.aRecommendation()
                .withAlgorithm(getAlgorithmById(algorithmId))
                .withMatrixId(getNewMatrixIndex(project))
                .withDate(endRecommendationTime)
                .withWeight(score)
                .withRuntimeInSeconds(calculateDifferenceTime(startRecommendationTime, endRecommendationTime))
                .withRecommendationRatings(new ArrayList<>())
                .withItem(item)
                .withEvaluator(evaluator)
                .build();

        recommendationService.save(recommendation);
        return recommendation;
    }

    public List<Recommendation> saveRecommendationList(List<Recommendation> recommendations, LocalDateTime startRecommendationTime,
                                                       Integer algorithmId) {
        List<Recommendation> savedRecommendations = new ArrayList<>();

        for(Recommendation recommendation: recommendations) {
            savedRecommendations.add(buildRecommendation(
                    recommendation.getWeight(), startRecommendationTime, algorithmId, recommendation.getItem(),
                    recommendation.getEvaluator(), recommendation.getItem().getProject()
            ));
        }

        return savedRecommendations;
    }

    public void defineNewMatrixId(Integer projectId) {
        projectService.updateMatrixId(projectId);
    }

    public Integer getNewMatrixIndex(Project projectMatrix) {
        Project project = projectService.find(projectMatrix.getId());
        return project.getLastMatrixId() + 1;
    }

    public Integer getNewMatrixIndexByProjectId(Integer projectId) {
        Project project = projectService.find(projectId);
        return project.getLastMatrixId() + 1;
    }

    public static Coordinate buildCoordinate(Double x, Double y) {
        Coordinate coordinate = new Coordinate();

        coordinate.setX(x);
        coordinate.setY(y);

        return coordinate;
    }

    public static CellPosition buildCellPosition(Integer row, Integer column) {
        CellPosition cellPosition = new CellPosition();

        cellPosition.setRow(row);
        cellPosition.setColumn(column);

        return cellPosition;
    }

    public static Item getItemByBasicPrimaryMatrix(Integer column, BasicBaseMatrix baseMatrix) {
        return baseMatrix.getItems().get(column);
    }

    public static Evaluator getEvaluatorByBasicPrimaryMatrix(Integer row, BasicBaseMatrix baseMatrix) {
        return baseMatrix.getEvaluators().get(row);
    }

    public static Double roundValue(Double value, Integer decimalPrecision) {
        return BigDecimal.valueOf(value)
                .setScale(decimalPrecision, RoundingMode.HALF_UP)
                .doubleValue();
    }

    public Integer calculateDifferenceTime(LocalDateTime startTime, LocalDateTime endTime) {
        return Math.toIntExact(ChronoUnit.MILLIS.between(startTime, endTime));
    }

    private Algorithm getAlgorithmById(Integer id) {
        return algorithmService.find(id);
    }
}
