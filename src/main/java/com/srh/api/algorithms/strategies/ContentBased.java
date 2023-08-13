package com.srh.api.algorithms.strategies;

import com.srh.api.algorithms.math.CellPosition;
import com.srh.api.algorithms.resources.*;
import com.srh.api.algorithms.resources.basedcontent.SimilarityEvaluatorContent;
import com.srh.api.algorithms.resources.basedcontent.EvaluatorProfileMatrix;
import com.srh.api.algorithms.resources.basedcontent.ItemTagMatrix;
import com.srh.api.algorithms.resources.basedcontent.SimilarityEvaluatorProfile;
import com.srh.api.algorithms.resources.utils.BasicBaseMatrix;
import com.srh.api.algorithms.resources.utils.RecommendationUtils;
import com.srh.api.algorithms.resources.utils.RecommendationsByEvaluator;
import com.srh.api.builder.AlgorithmBuilder;
import com.srh.api.builder.RecommendationBuilder;
import com.srh.api.dto.resource.RecommendationForm;
import com.srh.api.model.Algorithm;
import com.srh.api.model.Evaluator;
import com.srh.api.model.Item;
import com.srh.api.model.Recommendation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class ContentBased implements RecommendationAlgorithm {
    @Autowired
    private BasicBaseMatrix primaryMatrix;

    @Autowired
    private ItemTagMatrix itemTagMatrix;

    @Autowired
    private RecommendationUtils recommendationUtils;

    private final List<RecommendationsByEvaluator> recommendationsByEvaluators = new ArrayList<>();
    private List<CellPosition> recommendationsPositions = new ArrayList<>();
    private Double passingScore;
    private Integer decimalPrecision;
    private LocalDateTime startRecommendation;

    @Override
    public List<RecommendationsByEvaluator> calc(RecommendationForm form) {
        passingScore = form.getPassingScore();
        decimalPrecision = form.getDecimalPrecision();

        buildBasicMatrix(form.getProjectId());
        itemTagMatrix.build(primaryMatrix.getItems());

        for(Evaluator evaluator: primaryMatrix.getEvaluators()) {
            EvaluatorProfileMatrix evaluatorProfileMatrix = mountEvaluatorProfile(evaluator);
            RecommendationsByEvaluator recommendationsByEvaluator = calculateRecommendationByEvaluator(
                    evaluatorProfileMatrix, itemTagMatrix, evaluator);
            recommendationsByEvaluators.add(recommendationsByEvaluator);
        }

        recommendationUtils.defineNewMatrixId(form.getProjectId());
        return recommendationsByEvaluators;
    }

    private void buildBasicMatrix(Integer projectId) {
        primaryMatrix.build(projectId);
    }

    private EvaluatorProfileMatrix mountEvaluatorProfile(Evaluator evaluator) {
        EvaluatorProfileMatrix evaluatorProfileMatrix = new EvaluatorProfileMatrix();
        evaluatorProfileMatrix.build(evaluator, primaryMatrix, itemTagMatrix);
        return evaluatorProfileMatrix;
    }

    private RecommendationsByEvaluator calculateRecommendationByEvaluator(EvaluatorProfileMatrix evaluatorProfileMatrix, ItemTagMatrix itemTagMatrix, Evaluator evaluator) {
        SimilarityEvaluatorProfile similarityEvaluatorProfile = new SimilarityEvaluatorProfile(evaluatorProfileMatrix);
        SimilarityEvaluatorContent similarityEvaluatorContent = new SimilarityEvaluatorContent(
                similarityEvaluatorProfile.getContent(),
                itemTagMatrix.getContent(),
                primaryMatrix.getItems(),
                primaryMatrix.getTags()
        );

        startRecommendation = LocalDateTime.now();
        return getRecommendations(evaluator, similarityEvaluatorContent);
    }

    private RecommendationsByEvaluator getRecommendations(Evaluator evaluator, SimilarityEvaluatorContent similarityEvaluatorContent) {
        RecommendationsByEvaluator recommendationsByEvaluator = new RecommendationsByEvaluator();
        recommendationsByEvaluator.setEvaluator(evaluator);
        List<Recommendation> recommendations = new ArrayList<>();
        Integer evaluatorRow = primaryMatrix.getEvaluators().indexOf(evaluator);

        for(int j = 0; j < primaryMatrix.getColSize(); j++) {
            if (primaryMatrix.getContent()[evaluatorRow][j] == null) {
                Double recommendationScore = RecommendationUtils.roundValue(similarityEvaluatorContent.getRecommendationForItemIdx(j), decimalPrecision);

                if (recommendationScore >= passingScore) {
                    recommendationsPositions.add(registerRecommendationPosition(evaluatorRow, j));
                    recommendations.add(buildRecommendation(recommendationScore, evaluator, j, similarityEvaluatorContent));
                }
            }
        }

        recommendationsByEvaluator.setRecommendations(recommendations);
        recommendationsByEvaluator.setMatrixId(recommendationUtils.getNewMatrixIndex(primaryMatrix.getProject()));

        return recommendationsByEvaluator;
    }

    private CellPosition registerRecommendationPosition(Integer row, Integer column) {
        CellPosition recommendationPosition = new CellPosition();

        recommendationPosition.setRow(row);
        recommendationPosition.setColumn(column);

        return recommendationPosition;
    }

    private Recommendation buildRecommendation(Double score, Evaluator evaluator, Integer itemColumnIdx,
        SimilarityEvaluatorContent similarityEvaluatorContent) {
        Item item = similarityEvaluatorContent.getItemByIdx(itemColumnIdx);
        return recommendationUtils.buildRecommendation(score, startRecommendation, 2, item, evaluator,
                primaryMatrix.getProject());
    }

    @Override
    public List<CellPosition> getRecommendationsPositions() {
        return recommendationsPositions;
    }
}
