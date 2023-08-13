package com.srh.api.algorithms.resources.utils;

import com.srh.api.model.Evaluator;
import com.srh.api.model.Project;
import com.srh.api.model.Tag;

import java.util.List;

public abstract class BaseMatrix {
    protected Double[][] content;

    protected Project project;
    protected List<Evaluator> evaluators;
    protected List<Tag> tags;
    protected Integer rowSize;
    protected Integer colSize;

    public void build(Integer projectId) {}

    public Double[][] getContent() {
        return content;
    }

    public Project getProject() {
        return project;
    }

    public List<Evaluator> getEvaluators() {
        return evaluators;
    }

    public List<Tag> getTags() {
        return tags;
    }

    public Integer getRowSize() {
        return rowSize;
    }

    public Integer getColSize() {
        return colSize;
    }
}
