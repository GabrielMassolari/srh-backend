package com.srh.api.service;

import com.srh.api.model.Project;
import com.srh.api.model.ProjectEvaluator;
import com.srh.api.model.Evaluator;
import com.srh.api.utils.PageUtil;
import lombok.SneakyThrows;
import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectEvaluatorService {
    @Autowired
    private ProjectService projectService;

    @Autowired
    private EvaluatorService evaluatorService;

    public Evaluator findEvaluatorByProject(Integer projectId, Integer evaluatorId) {
        Project project = projectService.find(projectId);
        Evaluator evaluator = evaluatorService.find(evaluatorId);

        if (project.getEvaluators().contains(evaluator)) {
            return evaluator;
        }

        throw new ObjectNotFoundException(evaluatorId, Evaluator.class.getName());
    }

    public Page<Evaluator> listEvaluatorsByProject(Integer projectId, Pageable pageInfo) {
        Project project = projectService.find(projectId);
        List<Evaluator> evaluatorList = project.getEvaluators();

        PageUtil<Evaluator> pageUtil = new PageUtil<>(pageInfo, evaluatorList);
        return pageUtil.getPage();
    }

    @SneakyThrows
    public ProjectEvaluator save(Integer projectId, Integer evaluatorId) {
        Project project = projectService.find(projectId);
        Evaluator evaluator = evaluatorService.find(evaluatorId);

        ProjectEvaluator projectEvaluator = new ProjectEvaluator(project, evaluator);
        projectEvaluator.addEntities();
        persistEntities(projectEvaluator);

        return projectEvaluator;
    }

    @SneakyThrows
    public void delete(Integer projectId, Integer evaluatorId) {
        Project project = projectService.find(projectId);
        Evaluator evaluator = evaluatorService.find(evaluatorId);

        ProjectEvaluator projectEvaluator = new ProjectEvaluator(project, evaluator);
        projectEvaluator.removeEntities();
        persistEntities(projectEvaluator);
    }

    private void persistEntities(ProjectEvaluator projectEvaluator) {
        projectService.save(projectEvaluator.getProject());
        evaluatorService.save(projectEvaluator.getEvaluator());
    }
}
