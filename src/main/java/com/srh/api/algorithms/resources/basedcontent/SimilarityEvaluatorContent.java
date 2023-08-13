package com.srh.api.algorithms.resources.basedcontent;

import com.srh.api.model.Item;
import com.srh.api.model.Tag;

import java.util.List;

public class SimilarityEvaluatorContent {
    private List<Item> items;
    private List<Tag> tags;
    private Double[][] content;
    private Integer rowSize;
    private Integer colSize;

    private Double[][] similarityProfileContent;
    private Integer[][] profileItemContent;

    public SimilarityEvaluatorContent(Double[][] similarityProfileContent, Integer[][] profileItemContent,
                                      List<Item> items, List<Tag> tags) {
        this.items = items;
        this.tags = tags;
        this.rowSize = similarityProfileContent.length;
        this.colSize = similarityProfileContent[0].length - 2;
        this.content = new Double[rowSize][colSize];
        this.similarityProfileContent = similarityProfileContent;
        this.profileItemContent = profileItemContent;

        fillSimilarityMatrix();
        calculateRecommendation();
    }

    private void fillSimilarityMatrix() {
        for (int i = 0; i < rowSize; i++) {
            for (int j = 0; j < colSize; j++) {
                content[i][j] = calculateCellValue(i, j);
            }
        }
    }

    private Double calculateCellValue(Integer row, Integer column) {
        Integer similarityColIdx = similarityProfileContent[0].length - 1;

        if (similarityProfileContent[row][column] == null)
            return null;

        return similarityProfileContent[row][column] * similarityProfileContent[row][similarityColIdx];
    }

    private void calculateRecommendation() {
        for (int i = 0; i < rowSize; i++) {
            for (int j = 0; j < colSize; j++) {
                if (content[i][j] == null)
                    content[i][j] = calculateSimilarityForItem(i, j);
            }
        }
    }

    private Double calculateSimilarityForItem(Integer row, Integer column) {
        Double amount = 0.0;
        Double similarityAmount = 0.0;
        Integer similarityColIdx = similarityProfileContent[0].length - 1;

        if (profileItemContent[row][column].equals(0)) {
            return null;
        }

        for(int i = 0; i < rowSize; i++) {
            if (i == row)
                continue;

            if (content[i][column] != null && !content[i][column].equals(0.0)) {
                similarityAmount += similarityProfileContent[i][similarityColIdx];
                amount += content[i][column];
            }
        }

        if (amount.equals(0.0)) {
            return 0.0;
        }

        return amount / similarityAmount;
    }

    public Double getRecommendationForItemIdx(Integer itemIdx) {
        Double amount = 0.0;
        Integer nTags = 0;

        for(int j = 0; j < colSize; j++) {
            if (content[itemIdx][j] != null) {
                amount += content[itemIdx][j];
                nTags++;
            }
        }

        if (nTags.equals(0)) {
            return 0.0;
        }

        return amount / nTags;
    }

    public Item getItemByIdx(Integer idx) {
        return items.get(idx);
    }

    public Double[][] getContent() {
        return content;
    }
}
