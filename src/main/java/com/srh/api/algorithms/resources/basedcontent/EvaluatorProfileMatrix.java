package com.srh.api.algorithms.resources.basedcontent;

import com.srh.api.algorithms.resources.utils.BaseMatrix;
import com.srh.api.algorithms.resources.utils.BasicBaseMatrix;
import com.srh.api.model.Evaluator;

public class EvaluatorProfileMatrix extends BaseMatrix {
    private Integer evaluatorRow;

    public void build(Evaluator evaluator, BasicBaseMatrix primaryMatrix, ItemTagMatrix itemTagMatrix) {
        evaluatorRow = findEvaluatorRow(evaluator, primaryMatrix);
        rowSize = itemTagMatrix.getRowSize() + 2;
        colSize = itemTagMatrix.getColSize();
        content = new Double[rowSize][colSize];

        generateProfileMatrix(primaryMatrix, itemTagMatrix);
        calculateProfileValues(itemTagMatrix.getRowSize());
    }

    private void generateProfileMatrix(BasicBaseMatrix primaryMatrix, ItemTagMatrix itemTagMatrix) {
        Double[] evaluatorRatings = primaryMatrix.getContent()[evaluatorRow];

        for(int i = 0; i < itemTagMatrix.getRowSize(); i++) {
            for(int j = 0; j < colSize; j++) {
                content[i][j] = calculateProfileCell(evaluatorRatings[i], itemTagMatrix.getContent()[i][j]);
            }
        }
    }

    private void calculateProfileValues(Integer itemTagMatrixRowSize) {
        for(int j = 0; j < colSize; j++) {
            Double amount = 0.0;
            Integer nElements = 0;

            for(int i = 0; i < itemTagMatrixRowSize; i++) {
                amount += addAmount(content[i][j]);
                nElements += addNElements(content[i][j]);
            }

            content[itemTagMatrixRowSize][j] = amount;
            content[itemTagMatrixRowSize + 1][j] = calculateAverage(amount, nElements);
        }
    }

    private Double addAmount(Double value) {
        return value != null ? value : 0.0;
    }

    private Integer addNElements(Double value) {
        return value != null && value != 0.0 ? 1 : 0;
    }

    private Double calculateAverage(Double amount, Integer nElements) {
        if (nElements == 0)
            return 0.0;

        return amount / nElements;
    }

    private Double calculateProfileCell(Double itemRating, Integer itemTagAssociation) {
        if (itemRating == null)
            return null;

        return itemRating * itemTagAssociation;
    }

    private Integer findEvaluatorRow(Evaluator evaluator, BasicBaseMatrix primaryMatrix) {
        return primaryMatrix.getEvaluators().indexOf(evaluator);
    }

    public Integer getEvaluatorRow() {
        return evaluatorRow;
    }

    public Double[] getAvarageRow() {
        return content[rowSize - 1];
    }
}
