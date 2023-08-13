package com.srh.api.hypermedia;

import com.srh.api.controller.AttributeController;
import com.srh.api.dto.resource.AttributeDto;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class AttributeModelAssembler implements RepresentationModelAssembler<AttributeDto, EntityModel<AttributeDto>> {
    @Override
    public EntityModel<AttributeDto> toModel(AttributeDto attributeDto) {
        return new EntityModel<>(attributeDto,
                linkTo(methodOn(AttributeController.class).find(attributeDto.getId())).withSelfRel(),
                linkTo(methodOn(AttributeController.class).listAll(Pageable.unpaged())).withRel("attributes")
        );
    }
}
