package com.srh.api.controller;

import com.srh.api.dto.resource.ItemDto;
import com.srh.api.dto.resource.TypeItemDto;
import com.srh.api.dto.resource.TypeItemForm;
import com.srh.api.hypermedia.TypeItemModelAssembler;
import com.srh.api.model.Item;
import com.srh.api.model.TypeItem;
import com.srh.api.service.TypeItemService;
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
import java.util.List;

import static com.srh.api.dto.resource.TypeItemDto.*;

@RestController
@RequestMapping("/typeitens")
public class TypeItemController {
    @Autowired
    private TypeItemService typeItemService;

    @Autowired
    private TypeItemModelAssembler typeItemModelAssembler;

    @Autowired
    PagedResourcesAssembler<TypeItemDto> pagedResourcesAssembler;

    @Autowired
    PagedResourcesAssembler<ItemDto> itemDtoPagedResourcesAssembler;

    @GetMapping
    public PagedModel<EntityModel<TypeItemDto>> listAll(@PageableDefault(page = 0, size = 5) Pageable pageInfo) {
        Page<TypeItem> typeItems = typeItemService.findAll(pageInfo);
        return pagedResourcesAssembler.toModel(convert(typeItems));
    }

    @GetMapping("/{id}")
    public EntityModel<TypeItemDto> find(@PathVariable Integer id) {
        TypeItem typeItem = typeItemService.find(id);
        return typeItemModelAssembler.toModel(new TypeItemDto(typeItem));
    }

    @PostMapping
    public ResponseEntity<EntityModel<TypeItemDto>> create(@RequestBody @Valid TypeItemForm typeItemForm,
                                                           UriComponentsBuilder uriBuilder) {
        TypeItem typeItem = typeItemForm.build();
        typeItemService.save(typeItem);
        URI uri = uriBuilder.path("/typeitems/{id}").buildAndExpand(typeItem.getId()).toUri();
        return ResponseEntity.created(uri)
                .body(typeItemModelAssembler.toModel(new TypeItemDto(typeItem)));
    }

    @PutMapping("/{id}")
    @Transactional
    public EntityModel<TypeItemDto> update(@RequestBody @Valid TypeItemForm typeItemForm, @PathVariable Integer id) {
        TypeItem typeItem = typeItemForm.build();
        typeItem.setId(id);
        typeItem = typeItemService.update(typeItem);
        return typeItemModelAssembler.toModel(new TypeItemDto(typeItem));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        typeItemService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{typeItemId}/itens")
    public PagedModel<EntityModel<ItemDto>> findItensByTypeItem(
            @PathVariable Integer typeItemId,
            @PageableDefault(page = 0, size = 5) Pageable pageInfo
    ) {
        TypeItem typeItem = typeItemService.find(typeItemId);
        PageUtil<Item> pageUtil = new PageUtil<>(pageInfo, typeItem.getItens());
        return itemDtoPagedResourcesAssembler.toModel(ItemDto.convert(pageUtil.getPage()));
    }

    @GetMapping("/projects/{projectId}")
    public PagedModel<EntityModel<TypeItemDto>> listTypeItensByProject(
            @PathVariable Integer projectId,
            @PageableDefault(page = 0, size = 5) Pageable pageInfo)
    {
        List<TypeItem> typeItems = typeItemService.listByProject(projectId);
        PageUtil<TypeItem> pageUtil = new PageUtil<>(pageInfo, typeItems);
        return pagedResourcesAssembler.toModel(TypeItemDto.convert(pageUtil.getPage()));
    }
}
