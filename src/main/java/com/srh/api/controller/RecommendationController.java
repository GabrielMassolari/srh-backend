package com.srh.api.controller;

import com.srh.api.algorithms.resources.utils.RecommendationsByEvaluator;
import com.srh.api.dto.resource.RecommendationDto;
import com.srh.api.dto.resource.RecommendationForm;
import com.srh.api.dto.resource.RecommendationsByEvaluatorDto;
import com.srh.api.hypermedia.RecommendationModelAssembler;
import com.srh.api.model.Recommendation;
import com.srh.api.service.RecommendationService;
import com.srh.api.utils.PageUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.*;

import javax.transaction.Transactional;
import javax.validation.Valid;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import static com.srh.api.dto.resource.RecommendationDto.convert;

@RestController
@RequestMapping("/recommendations")
public class RecommendationController {
    @Autowired
    private RecommendationService recommendationService;

    @Autowired
    private RecommendationModelAssembler recommendationModelAssembler;

    @Autowired
    private PagedResourcesAssembler<RecommendationsByEvaluatorDto> recommendationsByEvaluatorModelAssembler;

    @Autowired
    private PagedResourcesAssembler<RecommendationDto> pagedResourcesAssembler;

    @GetMapping
    public PagedModel<EntityModel<RecommendationDto>> listAll(@PageableDefault(page = 0, size = 5)
                                                                      Pageable pageInfo) {
        Page<Recommendation> recommendations = recommendationService.findAll(pageInfo);
        return pagedResourcesAssembler.toModel(convert(recommendations));
    }

    @GetMapping("/{id}")
    public EntityModel<RecommendationDto> find(@PathVariable Integer id) {
        Recommendation recommendation = recommendationService.find(id);
        return recommendationModelAssembler.toModel(new RecommendationDto(recommendation));
    }

    @PostMapping
    PagedModel<EntityModel<RecommendationsByEvaluatorDto>> create(
            @RequestBody @Valid RecommendationForm recommendationForm,
            @PageableDefault(page = 0, size = 5) Pageable pageInfo) {
        List<RecommendationsByEvaluator> recommendationsByEvaluatorList = recommendationService.
                generateRecommendations(recommendationForm);
        PageUtil<RecommendationsByEvaluator> pageUtil = new PageUtil<>(pageInfo,
                recommendationsByEvaluatorList);
        return recommendationsByEvaluatorModelAssembler.toModel(RecommendationsByEvaluatorDto.convert(pageUtil.getPage()));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        recommendationService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/performances/{algorithmId}")
    public ResponseEntity<Page<RecommendationDto>> listRecommendationsPerformance(
            @PathVariable Integer algorithmId, @PageableDefault(page = 0, size = 5) Pageable pageInfo) {
        Page<Recommendation> recommendations = recommendationService.listRecommendationsByAlgorithm(
                algorithmId, pageInfo
        );
        return ResponseEntity.ok(RecommendationDto.convert(recommendations));
    }

    @GetMapping("/matrices/{matrixId}")
    public ResponseEntity<Page<RecommendationDto>> listRecommendationsMatrix(
            @PathVariable Integer matrixId, @PageableDefault(page = 0, size = 5) Pageable pageInfo) {
        Page<Recommendation> recommendations = recommendationService.listRecommendationsByMatrixId(
                matrixId, pageInfo);
        return ResponseEntity.ok(RecommendationDto.convert(recommendations));
    }

    @GetMapping("/tags/{tagId}")
    public ResponseEntity<Page<RecommendationDto>> listRecommendationsByTag(
            @PathVariable Integer tagId, @PageableDefault(page = 0, size = 5) Pageable pageInfo
    ) {
        Page<Recommendation> recommendations = recommendationService.listRecommendationsByTag(tagId, pageInfo);
        return ResponseEntity.ok(RecommendationDto.convert(recommendations));
    }

    @PostMapping("/async")
    public PagedModel<EntityModel<RecommendationsByEvaluatorDto>>
        createAsyncRecommendations(
            @RequestBody @Valid RecommendationForm recommendationForm,
            @PageableDefault(page = 0, size = 5) Pageable pageInfo
    ) throws ExecutionException, InterruptedException {
        CompletableFuture<List<RecommendationsByEvaluator>> listCompletableFuture = recommendationService
                .generateAsyncRecommendations(recommendationForm);

        PageUtil<RecommendationsByEvaluator> pageUtil = new PageUtil<>(pageInfo,
                listCompletableFuture.get());

        return recommendationsByEvaluatorModelAssembler
                .toModel(RecommendationsByEvaluatorDto.convert(pageUtil.getPage()));
    }

    @PostMapping("/offline")
    PagedModel<EntityModel<RecommendationsByEvaluatorDto>> createOffline(
            @RequestBody @Valid RecommendationForm recommendationForm,
            @PageableDefault(page = 0, size = 5) Pageable pageInfo) {
        List<RecommendationsByEvaluator> recommendationsByEvaluatorList = recommendationService.
                generateOfflineRecommendations(recommendationForm);
        PageUtil<RecommendationsByEvaluator> pageUtil = new PageUtil<>(pageInfo,
                recommendationsByEvaluatorList);
        return recommendationsByEvaluatorModelAssembler.toModel(RecommendationsByEvaluatorDto.convert(pageUtil.getPage()));
    }
}
