package com.srh.api.algorithms.resources.utils;

import com.srh.api.model.*;
import com.srh.api.service.ItemRatingService;
import com.srh.api.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BasicBaseMatrix extends BaseMatrix {
    @Autowired
    private ProjectService projectService;

    @Autowired
    private ItemRatingService itemRatingService;

    private List<Item> items;
    private List<ItemRating> itemRatings;

    public void build(Integer projectId) {
        project = projectService.find(projectId);
        evaluators = project.getEvaluators();
        items = project.getItens();
        itemRatings = projectService.listItemRatingsByProject(projectId);

        generateMatrix();
    }

    private void generateMatrix() {
        rowSize = evaluators.size();
        colSize = items.size();
        fillPrimaryMatrix();
    }

    private void fillPrimaryMatrix() {
        content = new Double[rowSize][colSize];

        for(int i = 0; i < rowSize; i++) {
            for(int j = 0; j < colSize; j++) {
                content[i][j] = getItemRatingInPosition(i, j);
            }
        }
    }

    private Double getItemRatingInPosition(Integer x, Integer y) {
        ItemRatingPK id = buildItemRatingId(x, y);

        try {
            ItemRating itemRating = itemRatingService.find(id);
            return itemRating.getScore();
        } catch (Exception e) {
            return null;
        }
    }

    private ItemRatingPK buildItemRatingId(Integer evaluatorPosition, Integer itemPosition) {
        ItemRatingPK id = new ItemRatingPK();

        id.setEvaluator(evaluators.get(evaluatorPosition));
        id.setItem(items.get(itemPosition));

        return id;
    }

    public List<Item> getItems() {
        return items;
    }
}
