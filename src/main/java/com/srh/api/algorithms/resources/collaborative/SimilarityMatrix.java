package com.srh.api.algorithms.resources.collaborative;

import com.srh.api.algorithms.math.Coordinate;
import com.srh.api.algorithms.math.EuclidianDistance;
import com.srh.api.algorithms.math.MathUtil;
import com.srh.api.algorithms.resources.utils.BaseMatrix;
import com.srh.api.algorithms.resources.utils.RecommendationUtils;
import com.srh.api.model.Evaluator;

import java.util.ArrayList;
import java.util.List;

public class SimilarityMatrix {
    private Double[][] content;
    private Integer rowSize;
    private Integer colSize;
    private Integer evaluatorRow;

    private EuclidianDistance euclidianDistance = new EuclidianDistance();

    public SimilarityMatrix(BaseMatrix baseMatrix, Evaluator evaluator) {
        rowSize = baseMatrix.getRowSize();
        colSize = baseMatrix.getColSize() + 2;
        evaluatorRow = getEvaluatorRow(evaluator, baseMatrix.getEvaluators());

        build(baseMatrix.getContent());
    }

    private void build(Double[][] primaryMatrixContent) {
        content = new Double[rowSize][colSize];

        for (int i = 0; i < rowSize; i++) {
            calculateCellsValues(primaryMatrixContent, i);
        }
    }

    private void calculateCellsValues(Double[][] primaryMatrix, Integer row) {
        List<Coordinate> coordinates = new ArrayList<>();

        for(int j = 0; j < colSize; j++) {
            coordinates.add(calculateCell(primaryMatrix, row, j, coordinates));
        }
    }

    private Coordinate calculateCell(Double[][] primaryMatrix, Integer row, Integer col,  List<Coordinate> coordinates) {
        Integer distanceColIndex = colSize - 2;
        Integer similarityColIndex = colSize - 1;

        if (col.equals(distanceColIndex)) {
            content[row][col] = euclidianDistance.calc(coordinates);
            return RecommendationUtils.buildCoordinate(null, null);
        }

        if (col.equals(similarityColIndex)) {
            content[row][col] = MathUtil.calculateSimilarity(euclidianDistance.calc(coordinates));
            return RecommendationUtils.buildCoordinate(null, null);
        }

        content[row][col] = primaryMatrix[row][col];
        return RecommendationUtils.buildCoordinate(primaryMatrix[evaluatorRow][col], content[row][col]);
    }

    private Integer getEvaluatorRow(Evaluator evaluator, List<Evaluator> evaluators) {
        return evaluators.indexOf(evaluator);
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

    public Double[][] getContent() {
        return content;
    }
}
