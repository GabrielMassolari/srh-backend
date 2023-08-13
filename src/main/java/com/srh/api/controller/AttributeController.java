package com.srh.api.controller;

import com.srh.api.dto.resource.AttributeDto;
import com.srh.api.dto.resource.AttributeForm;
import com.srh.api.hypermedia.AttributeModelAssembler;
import com.srh.api.model.Attribute;
import com.srh.api.service.AttributeService;
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

import static com.srh.api.dto.resource.AttributeDto.convert;

@RestController
@RequestMapping("/attributes")
public class AttributeController {
    @Autowired
    private AttributeService attributeService;

    @Autowired
    private AttributeModelAssembler attributeModelAssembler;

    @Autowired
    private PagedResourcesAssembler<AttributeDto> pagedResourcesAssembler;

    @GetMapping
    public PagedModel<EntityModel<AttributeDto>> listAll(@PageableDefault(page = 0, size = 5)
                                                                 Pageable pageInfo) {
        Page<Attribute> attributes = attributeService.findAll(pageInfo);
        return pagedResourcesAssembler.toModel(convert(attributes));
    }

    @GetMapping("/{id}")
    public EntityModel<AttributeDto> find(@PathVariable Integer id) {
        Attribute attribute = attributeService.find(id);
        return attributeModelAssembler.toModel(new AttributeDto(attribute));
    }

    @PostMapping
    public ResponseEntity<EntityModel<AttributeDto>> create(@RequestBody @Valid AttributeForm attributeForm,
                                                            UriComponentsBuilder uriBuilder) {
        Attribute attribute = attributeForm.build();
        attributeService.save(attribute);
        URI uri = uriBuilder.path("/attributes").buildAndExpand(attribute.getId()).toUri();
        return ResponseEntity.created(uri)
                .body(attributeModelAssembler.toModel(new AttributeDto(attribute)));
    }

    @PutMapping("/{id}")
    @Transactional
    public EntityModel<AttributeDto> update(@RequestBody @Valid AttributeForm attributeForm,
                                            @PathVariable Integer id) {
        Attribute attribute = attributeForm.build();
        attribute.setId(id);
        attribute = attributeService.update(attribute);
        return attributeModelAssembler.toModel(new AttributeDto(attribute));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        attributeService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
