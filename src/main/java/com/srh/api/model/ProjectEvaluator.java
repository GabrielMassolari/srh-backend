package com.srh.api.model;

import com.srh.api.error.exception.DuplicateValueException;
import com.srh.api.error.exception.RelationshipNotFoundException;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.SneakyThrows;

import java.util.List;

@Data
@AllArgsConstructor
public class ProjectEvaluator {
    private Project project;
    private Evaluator evaluator;

    @SneakyThrows
    public void addEntities() {
        addProjectInEvaluator();
        addEvaluatorInProject();
    }

    @SneakyThrows
    public void removeEntities() {
        removeProjectInEvaluator();
        removeEvaluatorInProject();
    }

    @SneakyThrows
    private void addEvaluatorInProject() {
        List<Evaluator> recommendersInProject = getEvaluatorListInProject();

        if (recommendersInProject.contains(evaluator))
            throw new DuplicateValueException("Evaluator link already exists");

        recommendersInProject.add(evaluator);
    }

    @SneakyThrows
    private void addProjectInEvaluator() {
        List<Project> projectsInEvaluator = getProjectListInEvaluator();

        if (projectsInEvaluator.contains(project))
            throw new DuplicateValueException("Project link already exists");

        projectsInEvaluator.add(project);
    }

    @SneakyThrows
    private void removeEvaluatorInProject() {
        List<Evaluator> recommendersInProject = getEvaluatorListInProject();

        if (!recommendersInProject.contains(evaluator))
            throw new RelationshipNotFoundException("Project not exist in Evaluator");

        recommendersInProject.remove(evaluator);
    }

    @SneakyThrows
    private void removeProjectInEvaluator() {
        List<Project> projectsInEvaluator = getProjectListInEvaluator();

        if (!projectsInEvaluator.contains(project))
            throw new RelationshipNotFoundException("Evaluator not exist in Project");

        projectsInEvaluator.remove(project);
    }

    private List<Evaluator> getEvaluatorListInProject() {
        return project.getEvaluators();
    }

    private List<Project> getProjectListInEvaluator() {
        return evaluator.getProjects();
    }
}
