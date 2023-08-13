package com.srh.api.hypermedia;

import com.srh.api.controller.AttributeController;
import com.srh.api.controller.TypeItemAttributeController;
import com.srh.api.controller.TypeItemController;
import com.srh.api.dto.resource.TypeItemAttributeDto;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class TypeItemAttributeModelAssembler implements RepresentationModelAssembler<TypeItemAttributeDto, EntityModel<TypeItemAttributeDto>> {
    @Override
    public EntityModel<TypeItemAttributeDto> toModel(TypeItemAttributeDto typeItemAttributeDto) {
        return new EntityModel<>(typeItemAttributeDto,
                linkTo(methodOn(TypeItemAttributeController.class).findAttributeInTypeItem(
                        typeItemAttributeDto.getTypeItemId(),
                        typeItemAttributeDto.getAttributeId()
                )).withSelfRel(),
                linkTo(methodOn(TypeItemAttributeController.class).listAttributesByTypeItem(
                        typeItemAttributeDto.getTypeItemId(),
                        Pageable.unpaged()
                )).withRel("attributes in typeItem"),
                linkTo(methodOn(TypeItemController.class).find(typeItemAttributeDto.getTypeItemId()))
                        .withRel("typeItem"),
                linkTo(methodOn(AttributeController.class).find(typeItemAttributeDto.getAttributeId()))
                        .withRel("attribute")
        );
    }
}
