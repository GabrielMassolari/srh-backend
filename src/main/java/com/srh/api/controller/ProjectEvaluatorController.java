package com.srh.api.controller;

import com.srh.api.dto.resource.ProjectEvaluatorDto;
import com.srh.api.dto.resource.ProjectEvaluatorForm;
import com.srh.api.dto.resource.EvaluatorDto;
import com.srh.api.hypermedia.ProjectEvaluatorModelAssembler;
import com.srh.api.hypermedia.EvaluatorModelAssembler;
import com.srh.api.model.ProjectEvaluator;
import com.srh.api.model.Evaluator;
import com.srh.api.service.ProjectEvaluatorService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/projects/{projectId}/evaluators")
public class ProjectEvaluatorController {
    @Autowired
    private ProjectEvaluatorService projectEvaluatorService;

    @Autowired
    private ProjectEvaluatorModelAssembler projectEvaluatorModelAssembler;

    @Autowired
    private EvaluatorModelAssembler evaluatorModelAssembler;

    @Autowired
    private PagedResourcesAssembler<EvaluatorDto> evaluatorDtoPagedResourcesAssembler;

    @GetMapping
    public PagedModel<EntityModel<EvaluatorDto>> listRecommendersByProject(
            @PathVariable Integer projectId, @PageableDefault(page = 0, size = 5) Pageable pageInfo) {
        Page<Evaluator> recommendersPage = projectEvaluatorService.
                listEvaluatorsByProject(projectId, pageInfo);
        return evaluatorDtoPagedResourcesAssembler.toModel(EvaluatorDto
                .convert(recommendersPage));
    }

    @GetMapping("/{evaluatorId}")
    public EntityModel<EvaluatorDto> findEvaluatorInProject(@PathVariable Integer projectId,
                                                            @PathVariable Integer evaluatorId) {
        Evaluator evaluator = projectEvaluatorService.findEvaluatorByProject(projectId,
                evaluatorId);
        return evaluatorModelAssembler.toModel(new EvaluatorDto(evaluator));
    }

    @PostMapping
    @SneakyThrows
    public ResponseEntity<EntityModel<ProjectEvaluatorDto>> linkEvaluatorsProject(
            @PathVariable Integer projectId, @RequestBody @Valid ProjectEvaluatorForm
            projectRecommenderForm, UriComponentsBuilder uriBuilder) {

        ProjectEvaluator projectEvaluator = projectEvaluatorService.save(
                projectRecommenderForm.getProjectId(),
                projectRecommenderForm.getEvaluatorId());

        URI uri = uriBuilder.path("/projects/{projectId}/recommenders/{evaluatorId}")
                .buildAndExpand(projectId, projectEvaluator.getEvaluator().getId())
                .toUri();

        return ResponseEntity.created(uri)
                .body(projectEvaluatorModelAssembler.toModel(
                        new ProjectEvaluatorDto(projectEvaluator)
                ));
    }

    @DeleteMapping("/{evaluatorId}")
    @SneakyThrows
    public ResponseEntity<Void> delete(@PathVariable Integer projectId,
                                       @PathVariable Integer evaluatorId) {
        projectEvaluatorService.delete(projectId, evaluatorId);
        return ResponseEntity.noContent().build();
    }
}
