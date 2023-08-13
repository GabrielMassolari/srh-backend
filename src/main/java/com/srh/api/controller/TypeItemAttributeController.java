package com.srh.api.controller;

import com.srh.api.dto.resource.AttributeDto;
import com.srh.api.dto.resource.TypeItemAttributeDto;
import com.srh.api.dto.resource.TypeItemAttributeForm;
import com.srh.api.hypermedia.AttributeModelAssembler;
import com.srh.api.hypermedia.TypeItemAttributeModelAssembler;
import com.srh.api.model.Attribute;
import com.srh.api.model.TypeItemAttribute;
import com.srh.api.service.TypeItemAttributeService;
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
@RequestMapping("/typeitens/{typeItemId}/attributes")
public class TypeItemAttributeController {
    @Autowired
    private TypeItemAttributeService typeItemAttributeService;

    @Autowired
    private TypeItemAttributeModelAssembler typeItemAttributeModelAssembler;

    @Autowired
    private AttributeModelAssembler attributeModelAssembler;

    @Autowired
    private PagedResourcesAssembler<AttributeDto> pagedResourcesAssembler;

    @GetMapping
    public PagedModel<EntityModel<AttributeDto>> listAttributesByTypeItem(
            @PathVariable Integer typeItemId, @PageableDefault(page = 0, size = 5) Pageable pageInfo
    ) {
        Page<Attribute> attributes = typeItemAttributeService.listAttributesByTypeItem(typeItemId, pageInfo);
        return pagedResourcesAssembler.toModel(AttributeDto.convert(attributes));
    }

    @GetMapping("/{attributeId}")
    public EntityModel<AttributeDto> findAttributeInTypeItem(@PathVariable Integer typeItemId,
                                                             @PathVariable Integer attributeId) {
        Attribute attribute = typeItemAttributeService.findAttributeByTypeItem(typeItemId, attributeId);
        return attributeModelAssembler.toModel(new AttributeDto(attribute));
    }

    @PostMapping
    @SneakyThrows
    public ResponseEntity<EntityModel<TypeItemAttributeDto>> linksAttributesTypeItem(
            @PathVariable Integer typeItemId,
            @RequestBody @Valid TypeItemAttributeForm typeItemAttributeForm,
            UriComponentsBuilder uriBuilder
    ) {

        TypeItemAttribute typeItemAttribute = typeItemAttributeService.save(
                typeItemAttributeForm.getTypeItemId(),
                typeItemAttributeForm.getAttributeId()
        );

        URI uri = uriBuilder.path("/projects/{projectId}/recommenders/{evaluatorId}")
                .buildAndExpand(typeItemId, typeItemAttributeForm.getAttributeId())
                .toUri();

        return ResponseEntity.created(uri)
                .body(typeItemAttributeModelAssembler.toModel(
                        new TypeItemAttributeDto(typeItemAttribute)
                ));
    }

    @DeleteMapping("/{attributeId}")
    @SneakyThrows
    public ResponseEntity<Void> delete(@PathVariable Integer typeItemId,
                                       @PathVariable Integer attributeId) {
        typeItemAttributeService.delete(typeItemId, attributeId);
        return ResponseEntity.noContent().build();
    }
}
