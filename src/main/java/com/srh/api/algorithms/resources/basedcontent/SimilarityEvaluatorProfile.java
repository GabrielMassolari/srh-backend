package com.srh.api.algorithms.resources.basedcontent;

import com.srh.api.algorithms.math.Coordinate;
import com.srh.api.algorithms.math.EuclidianDistance;
import com.srh.api.algorithms.math.MathUtil;
import com.srh.api.algorithms.resources.utils.RecommendationUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SimilarityEvaluatorProfile {
    private Double[][] content;
    private Integer rowSize;
    private Integer colSize;
    private Integer evaluatorRow;
    private EuclidianDistance euclidianDistance = new EuclidianDistance();
    private EvaluatorProfileMatrix evaluatorProfileMatrix;

    public SimilarityEvaluatorProfile(EvaluatorProfileMatrix evaluatorProfileMatrixContent) {
        rowSize = evaluatorProfileMatrixContent.getRowSize() - 2;
        colSize = evaluatorProfileMatrixContent.getColSize() + 2;
        evaluatorProfileMatrix = evaluatorProfileMatrixContent;

        evaluatorRow = evaluatorProfileMatrix.getEvaluatorRow();
        content = new Double[rowSize][colSize];

        fillSimilarityBase();
        calculateDistance();
        calculateSimilarity();
    }

    private void calculateDistance() {
        Integer colDistanceIdx = evaluatorProfileMatrix.getColSize();

        for(int i = 0; i < rowSize; i++) {
            content[i][colDistanceIdx] = calculateDistanceRow(i);
        }
    }

    private void calculateSimilarity() {
        Integer distanceColIdx = evaluatorProfileMatrix.getColSize();
        Integer similarityColIdx = evaluatorProfileMatrix.getColSize() + 1;

        for(int i = 0; i < rowSize; i++) {
            content[i][similarityColIdx] = MathUtil.calculateSimilarity(content[i][distanceColIdx]);
        }
    }

    private Double calculateDistanceRow(Integer row) {
        List<Coordinate> coordinates = new ArrayList<>();
        Integer colLimitIdx = evaluatorProfileMatrix.getColSize();

        for(int j = 0; j < colLimitIdx; j++) {
            coordinates.add(getCoordinate(row, j));
        }

        return euclidianDistance.calc(coordinates);
    }

    private Coordinate getCoordinate(Integer row, Integer column) {
        Double value = content[row][column];

        if (value == null || value.equals(0.0))
            return RecommendationUtils.buildCoordinate(null, null);

        Double profileValue = evaluatorProfileMatrix.getAvarageRow()[column];
        return RecommendationUtils.buildCoordinate(profileValue, value);
    }

    private void fillSimilarityBase() {
        for (int i = 0; i < rowSize; i++) {
            content[i] = Arrays.copyOf(evaluatorProfileMatrix.getContent()[i], colSize);
        }
    }

    public Double[][] getContent() {
        return content;
    }

    public Integer getRowSize() {
        return rowSize;
    }

    public Integer getColSize() {
        return colSize;
    }

    public Integer getEvaluatorRow() {
        return evaluatorRow;
    }
}
