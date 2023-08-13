package com.srh.api.controller;

import com.srh.api.dto.resource.EvaluatorDto;
import com.srh.api.dto.resource.EvaluatorForm;
import com.srh.api.dto.resource.RecommendationDto;
import com.srh.api.hypermedia.EvaluatorModelAssembler;
import com.srh.api.model.Evaluator;
import com.srh.api.model.Recommendation;
import com.srh.api.model.TypeItem;
import com.srh.api.service.EvaluatorService;
import com.srh.api.utils.PageUtil;
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

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.net.URI;

import static com.srh.api.dto.resource.EvaluatorDto.convert;

@RestController
@RequestMapping("/users/evaluators")
public class EvaluatorController {
    @Autowired
    private EvaluatorService evaluatorService;

    @Autowired
    private EvaluatorModelAssembler evaluatorModelAssembler;

    @Autowired
    private PagedResourcesAssembler<EvaluatorDto> pagedResourcesAssembler;

    @Autowired
    private PagedResourcesAssembler<RecommendationDto> recommendationDtoPagedResourcesAssembler;

    @GetMapping
    public PagedModel<EntityModel<EvaluatorDto>> listAll(@PageableDefault(page = 0, size = 5)
                                                                          Pageable pageInfo) {
        Page<Evaluator> users = evaluatorService.findAll(pageInfo);
        return pagedResourcesAssembler.toModel(convert(users));
    }

    @GetMapping("/{id}")
    public EntityModel<EvaluatorDto> find(@PathVariable Integer id) {
        Evaluator evaluator = evaluatorService.find(id);
        return evaluatorModelAssembler.toModel(new EvaluatorDto(evaluator));
    }

    @PostMapping
    public ResponseEntity<EntityModel<EvaluatorDto>> create(@RequestBody @Valid EvaluatorForm evaluatorForm,
                                                            UriComponentsBuilder uriBuilder) {
        Evaluator evaluator = evaluatorForm.build();
        evaluatorService.save(evaluator);
        URI uri = uriBuilder.path("/users/evaluators/{id}").buildAndExpand(evaluator.getId()).toUri();
        return ResponseEntity.created(uri)
                .body(evaluatorModelAssembler.toModel(new EvaluatorDto(evaluator)));
    }

    @PutMapping("/{id}")
    @Transactional
    public EntityModel<EvaluatorDto> update(@RequestBody @Valid EvaluatorForm evaluatorForm,
                                            @PathVariable Integer id) {
        Evaluator evaluator = evaluatorForm.build();
        evaluator.setId(id);
        evaluator = evaluatorService.update(evaluator, evaluatorForm.getOldPassword());
        return evaluatorModelAssembler.toModel(new EvaluatorDto(evaluator));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        evaluatorService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{evaluatorId}/recommendations")
    public PagedModel<EntityModel<RecommendationDto>> listRecommendationsByEvaluator(
            @PathVariable Integer evaluatorId,
            @PageableDefault(page = 0, size = 5) Pageable pageInfo
                                                                                ) {
        PageUtil<Recommendation> pageUtil = new PageUtil<>(pageInfo, evaluatorService.
                listRecommendationsByEvaluator(evaluatorId));

        return recommendationDtoPagedResourcesAssembler.toModel(RecommendationDto.convert(
                pageUtil.getPage()
        ));
    }
}
