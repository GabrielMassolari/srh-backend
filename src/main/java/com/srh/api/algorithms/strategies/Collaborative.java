package com.srh.api.algorithms.strategies;

import com.srh.api.algorithms.math.CellPosition;
import com.srh.api.algorithms.resources.*;
import com.srh.api.algorithms.resources.collaborative.SimilarityMatrix;
import com.srh.api.algorithms.resources.collaborative.SimilarityMatrixEvaluator;
import com.srh.api.algorithms.resources.utils.BasicBaseMatrix;
import com.srh.api.algorithms.resources.utils.RecommendationUtils;
import com.srh.api.algorithms.resources.utils.RecommendationsByEvaluator;
import com.srh.api.dto.resource.RecommendationForm;
import com.srh.api.model.Evaluator;
import com.srh.api.model.Item;
import com.srh.api.model.Recommendation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class Collaborative implements RecommendationAlgorithm {
    @Autowired
    private BasicBaseMatrix primaryMatrix;

    @Autowired
    private RecommendationUtils recommendationUtils;

    private final List<RecommendationsByEvaluator> recommendationsByEvaluators = new ArrayList<>();
    private final List<CellPosition> recommendationsPositions = new ArrayList<>();
    private Double passingScore;
    private Integer decimalPrecision;
    private LocalDateTime startTime;

    @Override
    public List<RecommendationsByEvaluator> calc(RecommendationForm form) {
        passingScore = form.getPassingScore();
        decimalPrecision = form.getDecimalPrecision();

        buildBasicMatrix(form.getProjectId());

        for(Evaluator evaluator: primaryMatrix.getEvaluators()) {
            recommendationsByEvaluators.add(calculateRecommendationByEvaluator(evaluator));
        }

        recommendationUtils.defineNewMatrixId(form.getProjectId());
        return recommendationsByEvaluators;
    }

    private void buildBasicMatrix(Integer projectId) {
        primaryMatrix.build(projectId);
    }

    private RecommendationsByEvaluator calculateRecommendationByEvaluator(Evaluator evaluator) {
        RecommendationsByEvaluator recommendationsByEvaluator = new RecommendationsByEvaluator();
        SimilarityMatrix similarityMatrix = new SimilarityMatrix(primaryMatrix, evaluator);
        SimilarityMatrixEvaluator similarityMatrixEvaluator = new SimilarityMatrixEvaluator(similarityMatrix, evaluator);
        Integer evaluatorIdx = primaryMatrix.getEvaluators().indexOf(evaluator);
        List<Recommendation> recommendations = new ArrayList<>();

        for(int column = 0; column < primaryMatrix.getColSize(); column++) {
            if (primaryMatrix.getContent()[evaluatorIdx][column] == null) {
                CellPosition cellPosition = RecommendationUtils.buildCellPosition(evaluatorIdx, column);
                Recommendation recommendation = generateRecommendation(cellPosition, similarityMatrix, similarityMatrixEvaluator);

                if (recommendation.getWeight() >= passingScore) {
                    recommendationsPositions.add(cellPosition);
                    recommendations.add(recommendation);
                }
            }
        }

        recommendationsByEvaluator.setEvaluator(evaluator);
        recommendationsByEvaluator.setRecommendations(recommendations);
        recommendationsByEvaluator.setMatrixId(recommendationUtils.getNewMatrixIndex(primaryMatrix.getProject()));

        return recommendationsByEvaluator;
    }

    private Recommendation generateRecommendation(CellPosition position, SimilarityMatrix similarityMatrix, SimilarityMatrixEvaluator similarityMatrixEvaluator) {
        startTime = LocalDateTime.now();

        Integer similarityIdx = similarityMatrix.getColSize() - 1;
        Double sumSimilarityItem = calculateSumOfSimilarityItem(position.getColumn(), similarityMatrixEvaluator);
        Double sumSimilarity = calculateSumOfSimilarity(similarityIdx, position.getColumn(), similarityMatrix);

        Evaluator evaluator = RecommendationUtils.getEvaluatorByBasicPrimaryMatrix(position.getRow(), primaryMatrix);
        Item item = RecommendationUtils.getItemByBasicPrimaryMatrix(position.getColumn(), primaryMatrix);

        if (sumSimilarity.equals(0.0))
            return recommendationUtils.buildRecommendation(0.0, startTime, 1, item, evaluator,
                    primaryMatrix.getProject());

        Double recommendationScore = sumSimilarityItem / sumSimilarity;
        recommendationScore = RecommendationUtils.roundValue(recommendationScore, decimalPrecision);

        return recommendationUtils.buildRecommendation(recommendationScore, startTime, 1, item, evaluator,
                primaryMatrix.getProject());
    }

    private Double calculateSumOfSimilarityItem(Integer column, SimilarityMatrixEvaluator similarityMatrixEvaluator) {
        Double amount = 0.0;

        for(int row = 0; row < similarityMatrixEvaluator.getRowSize(); row++) {
            if (similarityMatrixEvaluator.getContent()[row][column] != null) {
                amount += similarityMatrixEvaluator.getContent()[row][column];
            }
        }

        return amount;
    }

    private Double calculateSumOfSimilarity(Integer column, Integer itemColumn, SimilarityMatrix similarityMatrix) {
        Double amount = 0.0;

        for(int row = 0; row < similarityMatrix.getRowSize(); row++) {
            if (similarityMatrix.getContent()[row][column] != null && similarityMatrix.getContent()[row][itemColumn] != null) {
                amount += similarityMatrix.getContent()[row][column];
            }
        }

        return amount;
    }

    @Override
    public List<CellPosition> getRecommendationsPositions() {
        return recommendationsPositions;
    }
}
