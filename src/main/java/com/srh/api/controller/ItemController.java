package com.srh.api.controller;

import com.srh.api.dto.resource.ItemDto;
import com.srh.api.dto.resource.ItemForm;
import com.srh.api.dto.resource.RecommendationDto;
import com.srh.api.hypermedia.ItemModelAssembler;
import com.srh.api.model.Item;
import com.srh.api.model.Recommendation;
import com.srh.api.service.ItemService;
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

import static com.srh.api.dto.resource.ItemDto.convert;

@RestController
@RequestMapping("/itens")
public class ItemController {
    @Autowired
    private ItemService itemService;

    @Autowired
    private ItemModelAssembler itemModelAssembler;

    @Autowired
    private PagedResourcesAssembler<ItemDto> pagedResourcesAssembler;

    @Autowired
    private PagedResourcesAssembler<RecommendationDto> recommendationsPagedResourcesAssembler;

    @GetMapping
    public PagedModel<EntityModel<ItemDto>> listAll(@PageableDefault(page = 0, size = 5)
                                                            Pageable pageInfo) {
        Page<Item> itens = itemService.findAll(pageInfo);
        return pagedResourcesAssembler.toModel(convert(itens));
    }

    @GetMapping("/{id}")
    public EntityModel<ItemDto> find(@PathVariable Integer id) {
        Item item = itemService.find(id);
        return itemModelAssembler.toModel(new ItemDto(item));
    }

    @PostMapping
    public ResponseEntity<EntityModel<ItemDto>> create(@RequestBody @Valid ItemForm itemForm,
                                                       UriComponentsBuilder uriBuilder) {
        Item item = itemForm.build();
        itemService.save(item);
        URI uri = uriBuilder.path("/itens/{id}").buildAndExpand(item.getId()).toUri();
        return ResponseEntity.created(uri)
                .body(itemModelAssembler.toModel(new ItemDto(item)));
    }

    @PutMapping("/{id}")
    @Transactional
    public EntityModel<ItemDto> update(@RequestBody @Valid ItemForm itemForm, @PathVariable Integer id) {
        Item item = itemForm.build();
        item.setId(id);
        item = itemService.update(item);
        return itemModelAssembler.toModel(new ItemDto(item));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        itemService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{itemId}/recommendations")
    public PagedModel<EntityModel<RecommendationDto>> listRecommendationsByItem(
            @PathVariable Integer itemId,
            @PageableDefault(page = 0, size = 5) Pageable pageInfo
    ) {
        Item item = itemService.find(itemId);
        PageUtil<Recommendation> pageUtil = new PageUtil<>(pageInfo, item.getRecommendations());
        return recommendationsPagedResourcesAssembler.toModel(RecommendationDto.convert(pageUtil.getPage()));
    }
}
