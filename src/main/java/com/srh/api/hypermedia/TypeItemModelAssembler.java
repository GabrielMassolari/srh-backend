package com.srh.api.hypermedia;

import com.srh.api.controller.TypeItemAttributeController;
import com.srh.api.controller.TypeItemController;
import com.srh.api.dto.resource.TypeItemDto;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
public class TypeItemModelAssembler implements RepresentationModelAssembler<TypeItemDto, EntityModel<TypeItemDto>> {
    @Override
    public EntityModel<TypeItemDto> toModel(TypeItemDto typeItemDto) {
        return new EntityModel<>(
                typeItemDto,
                linkTo(methodOn(TypeItemController.class).find(typeItemDto.getId())).withSelfRel(),
                linkTo(methodOn(TypeItemController.class).listAll(Pageable.unpaged())).withRel("typeitems"),
                linkTo(methodOn(TypeItemController.class).findItensByTypeItem(typeItemDto.getId(), Pageable.unpaged()))
                        .withRel("itens"),
                linkTo(methodOn(TypeItemAttributeController.class).listAttributesByTypeItem(typeItemDto.getId(), Pageable.unpaged()))
                        .withRel("attributes")
        );
    }
}
