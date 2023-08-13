package com.srh.api.algorithms.resources.basedcontent;

import com.srh.api.algorithms.resources.utils.BaseMatrix;
import com.srh.api.model.Item;
import com.srh.api.model.Tag;
import com.srh.api.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ItemProfile {
    @Autowired
    private ProjectService projectService;

    Double[][] content;
    List<Item> items = new ArrayList<>();
    List<Tag> tags = new ArrayList<>();

    private Integer rowSize;
    private Integer colSize;

    public void build(BaseMatrix baseMatrix, Integer projectId) {
        tags = projectService.listTagsByProject(projectId);
        items = projectService.listItensByProject(projectId);
    }

    private void buildMatrix(BaseMatrix baseMatrix) {
        rowSize = items.size();
        colSize = tags.size();
        content = new Double[rowSize][colSize];
    }
}
