package com.srh.api.controller;

import com.srh.api.dto.resource.RecommendationRatingDto;
import com.srh.api.dto.resource.RecommendationRatingForm;
import com.srh.api.hypermedia.RecommendationRatingModelAssembler;
import com.srh.api.model.RecommendationRating;
import com.srh.api.service.RecommmendationRatingService;
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

import static com.srh.api.dto.resource.RecommendationRatingDto.convert;

@RestController
@RequestMapping("/recommendationratings")
public class RecommendationRatingController {
    @Autowired
    private RecommmendationRatingService recommmendationRatingService;

    @Autowired
    private RecommendationRatingModelAssembler recommendationRatingModelAssembler;

    @Autowired
    private PagedResourcesAssembler<RecommendationRatingDto> pagedResourcesAssembler;

    @GetMapping
    public PagedModel<EntityModel<RecommendationRatingDto>> listAll(@PageableDefault(page = 0, size = 5)
                                                                            Pageable pageInfo) {
        Page<RecommendationRating> recommendationRatingDtos = recommmendationRatingService.findAll(pageInfo);
        return pagedResourcesAssembler.toModel(convert(recommendationRatingDtos));
    }

    @GetMapping("/{id}")
    public EntityModel<RecommendationRatingDto> find(@PathVariable Integer id) {
        RecommendationRating recommendationRating = recommmendationRatingService.find(id);
        return recommendationRatingModelAssembler.toModel(new RecommendationRatingDto(recommendationRating));
    }

    @PostMapping
    public ResponseEntity<EntityModel<RecommendationRatingDto>> create(@RequestBody @Valid
                                                                               RecommendationRatingForm recommendationRatingForm,
                                                                       UriComponentsBuilder uriBuilder) {
        RecommendationRating recommendationRating = recommendationRatingForm.build();
        recommmendationRatingService.save(recommendationRating);
        URI uri = uriBuilder.path("/recommendationratings/{id}").buildAndExpand(recommendationRating.getId()).toUri();
        return ResponseEntity.created(uri)
                .body(recommendationRatingModelAssembler.toModel(new RecommendationRatingDto(recommendationRating)));
    }

    @PutMapping("/{id}")
    @Transactional
    public EntityModel<RecommendationRatingDto> update(@RequestBody @Valid RecommendationRatingForm recommendationRatingForm,
                                                       @PathVariable Integer id) {
        RecommendationRating recommendationRating = recommendationRatingForm.build();
        recommendationRating.setId(id);
        recommendationRating = recommmendationRatingService.update(recommendationRating);
        return recommendationRatingModelAssembler.toModel(new RecommendationRatingDto(recommendationRating));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        recommmendationRatingService.delete(id);
        return ResponseEntity.noContent().build();
    }
}