package com.srh.api.algorithms.resources.collaborative;

import com.srh.api.model.Evaluator;

public class SimilarityMatrixEvaluator {
    Evaluator evaluator;
    Double[][] content;

    Integer rowSize;
    Integer colSize;
    Integer similarityIndex;
    Integer evaluatorRow;

    public SimilarityMatrixEvaluator(SimilarityMatrix similarityMatrix, Evaluator evaluator) {
        this.evaluator = evaluator;
        build(similarityMatrix);
    }

    private void build(SimilarityMatrix similarityMatrix) {
        rowSize = similarityMatrix.getRowSize();
        colSize = similarityMatrix.getColSize() - 2;
        similarityIndex = similarityMatrix.getColSize() - 1;
        evaluatorRow = similarityMatrix.getEvaluatorRow();

        content = new Double[rowSize][colSize];
        calculateSimilarityByItem(similarityMatrix.getContent());
    }

    private void calculateSimilarityByItem(Double[][] similarityMatrix) {
        for (int i = 0; i < rowSize; i++) {
            calculateSimilarityByCell(i, similarityMatrix);
        }
    }

    private void calculateSimilarityByCell(Integer i, Double[][] similarityMatrix) {
        for(int j = 0; j < colSize; j++) {
            content[i][j] = calculateSimilarity(
                    similarityMatrix[i][j], similarityMatrix[i][similarityIndex], i);
        }
    }

    private Double calculateSimilarity(Double itemScore, Double similarityValue, Integer row) {
        // Os IFs tem que está nessa ordem
        if (evaluatorRow.equals(row)) {
            return null;
        }

        if (itemScore == null)
            return 0.0;

        return itemScore * similarityValue;
    }

    public Double[][] getContent() {
        return content;
    }

    public Integer getRowSize() {
        return rowSize;
    }
}
