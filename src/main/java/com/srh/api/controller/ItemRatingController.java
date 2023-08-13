package com.srh.api.controller;

import com.srh.api.builder.ItemRatingPKBuilder;
import com.srh.api.dto.resource.ItemRatingDto;
import com.srh.api.dto.resource.ItemRatingForm;
import com.srh.api.hypermedia.ItemRatingModelAssembler;
import com.srh.api.model.ItemRating;
import com.srh.api.model.ItemRatingPK;
import com.srh.api.service.ItemRatingService;
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

import static com.srh.api.dto.resource.ItemRatingDto.convert;

@RestController
@RequestMapping("/itemratings")
public class ItemRatingController {
    @Autowired
    private ItemRatingService itemRatingService;

    @Autowired
    private ItemRatingModelAssembler itemRatingModelAssembler;

    @Autowired
    PagedResourcesAssembler<ItemRatingDto> pagedResourcesAssembler;

    @GetMapping
    public PagedModel<EntityModel<ItemRatingDto>> listAll(@PageableDefault(page = 0, size = 5)
                                                                  Pageable pageInfo) {
        Page<ItemRating> itemRatings = itemRatingService.findAll(pageInfo);
        return pagedResourcesAssembler.toModel(convert(itemRatings));
    }

    @GetMapping("/itens/{itemId}/evaluators/{evaluatorId}")
    public EntityModel<ItemRatingDto> find(@PathVariable Integer itemId, @PathVariable Integer evaluatorId) {
        ItemRatingForm itemRatingForm = new ItemRatingForm(null, evaluatorId, itemId);
        ItemRating itemRatingGet = itemRatingForm.build();

        ItemRating itemRating = itemRatingService.find(itemRatingGet.getId());
        return itemRatingModelAssembler.toModel(new ItemRatingDto(itemRating));
    }

    @PostMapping
    public ResponseEntity<EntityModel<ItemRatingDto>> create(@RequestBody @Valid ItemRatingForm itemRatingForm,
                                                             UriComponentsBuilder uriBuilder) {
        ItemRating itemRating = itemRatingForm.build();
        itemRatingService.save(itemRating);
        URI uri = uriBuilder.path("/itemratings/itens/{itemId}/evaluators/{evaluatorId}")
                .buildAndExpand(itemRating.getId().getItem().getId(),
                        itemRating.getId().getEvaluator().getId()).toUri();
        return ResponseEntity.created(uri)
                .body(itemRatingModelAssembler.toModel(new ItemRatingDto(itemRating)));
    }

    @PutMapping("/itens/{itemId}/evaluators/{evaluatorId}")
    @Transactional
    public EntityModel<ItemRatingDto> update(@RequestBody @Valid ItemRatingForm itemRatingForm,
                                             @PathVariable Integer itemId, @PathVariable Integer evaluatorId) {
        ItemRating itemRating = itemRatingForm.build();
        itemRating = itemRatingService.update(itemRating, evaluatorId, itemId);
        return itemRatingModelAssembler.toModel(new ItemRatingDto(itemRating));
    }

    @DeleteMapping("/itens/{itemId}/evaluators/{evaluatorId}")
    public ResponseEntity<Void> delete(@PathVariable Integer itemId, @PathVariable Integer evaluatorId) {
        ItemRatingForm itemRatingForm = new ItemRatingForm(null, evaluatorId, itemId);
        ItemRating itemRatingGet = itemRatingForm.build();
        itemRatingService.delete(itemRatingGet.getId());
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/itens/{itemId}")
    public ResponseEntity<Page<ItemRatingDto>> listItensRatingsByItem(
            @PathVariable Integer itemId,
            @PageableDefault(page = 0, size = 5) Pageable pageInfo
    ) {
        Page<ItemRating> itensRatings = itemRatingService.listItensRatingsByItem(itemId, pageInfo);
        return ResponseEntity.ok(ItemRatingDto.convert(itensRatings));
    }

    @GetMapping("/tags/{tagId}")
    public ResponseEntity<Page<ItemRatingDto>> listItensRatingsByTag(
            @PathVariable Integer tagId,
            @PageableDefault(page = 0, size = 5) Pageable pageInfo
    ) {
        Page<ItemRating> itensRatings = itemRatingService.listItensRAtingsByTag(tagId, pageInfo);
        return ResponseEntity.ok(ItemRatingDto.convert(itensRatings));
    }

    @GetMapping("/evaluators/{evaluatorId}")
    public ResponseEntity<Page<ItemRatingDto>> listItensRatingsByEvaluator(
            @PathVariable Integer evaluatorId,
            @PageableDefault(page = 0, size = 5) Pageable pageInfo) {
        Page<ItemRating> itensRatings = itemRatingService.listItensRatingsByEvaluator(evaluatorId, pageInfo);
        return ResponseEntity.ok(ItemRatingDto.convert(itensRatings));
    }

    @GetMapping("/projects/{projectId}")
    public ResponseEntity<Page<ItemRatingDto>> listItensRatingsByProject(
            @PathVariable Integer projectId,
            @PageableDefault(page = 0, size = 5) Pageable pageInfo) {
        Page<ItemRating> itensRatings = itemRatingService.listItensRatingsByProject(projectId, pageInfo);
        return ResponseEntity.ok(ItemRatingDto.convert(itensRatings));
    }
}
