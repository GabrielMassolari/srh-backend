package com.srh.api.controller;

import com.srh.api.dto.resource.AttributeDto;
import com.srh.api.dto.resource.ItemAttributeDto;
import com.srh.api.dto.resource.ItemAttributeForm;
import com.srh.api.hypermedia.AttributeModelAssembler;
import com.srh.api.hypermedia.ItemAttributeModelAssembler;
import com.srh.api.model.Attribute;
import com.srh.api.model.ItemAttribute;
import com.srh.api.service.ItemAttributeService;
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
@RequestMapping("/itens/{itemId}/attributes")
public class ItemAttributeController {
    @Autowired
    private ItemAttributeService itemAttributeService;

    @Autowired
    private ItemAttributeModelAssembler itemAttributeModelAssembler;

    @Autowired
    private AttributeModelAssembler attributeModelAssembler;

    @Autowired
    PagedResourcesAssembler<AttributeDto> pagedResourcesAssembler;

    @GetMapping
    public PagedModel<EntityModel<AttributeDto>> listAttributesByItem(
            @PathVariable Integer itemId, @PageableDefault(page = 0, size = 5) Pageable pageInfo
    ) {
        Page<Attribute> attributes = itemAttributeService.listAttributesByItem(itemId, pageInfo);
        return pagedResourcesAssembler.toModel(AttributeDto.convert(attributes));
    }

    @GetMapping("/{attributeId}")
    public EntityModel<AttributeDto> findAttributeInItem(@PathVariable Integer itemId,
                                                         @PathVariable Integer attributeId) {
        Attribute attribute = itemAttributeService.findAttributeByItem(itemId, attributeId);
        return attributeModelAssembler.toModel(new AttributeDto(attribute));
    }

    @PostMapping
    @SneakyThrows
    public ResponseEntity<EntityModel<ItemAttributeDto>> linksAttributesItem(
            @PathVariable Integer itemId,
            @RequestBody @Valid ItemAttributeForm itemAttributeForm,
            UriComponentsBuilder uriBuilder
    ) {
        ItemAttribute itemAttribute = itemAttributeService.save(
                itemAttributeForm.getItemId(),
                itemAttributeForm.getAttributeId()
        );

        URI uri = uriBuilder.path("/itens/{itemId}/attributes/{attributeId}")
                .buildAndExpand(itemId, itemAttributeForm.getAttributeId())
                .toUri();

        return ResponseEntity.created(uri)
                .body(itemAttributeModelAssembler.toModel(
                        new ItemAttributeDto(itemAttribute)
                ));
    }

    @DeleteMapping("/{attributeId}")
    @SneakyThrows
    public ResponseEntity<Void> delete(@PathVariable Integer itemId,
                                       @PathVariable Integer attributeId) {
        itemAttributeService.delete(itemId, attributeId);
        return ResponseEntity.noContent().build();
    }
}
