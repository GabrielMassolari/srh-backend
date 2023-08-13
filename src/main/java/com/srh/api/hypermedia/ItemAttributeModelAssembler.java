package com.srh.api.hypermedia;

import com.srh.api.controller.AttributeController;
import com.srh.api.controller.ItemAttributeController;
import com.srh.api.controller.ItemController;
import com.srh.api.dto.resource.ItemAttributeDto;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class ItemAttributeModelAssembler implements RepresentationModelAssembler<ItemAttributeDto,
        EntityModel<ItemAttributeDto>> {
    @Override
    public EntityModel<ItemAttributeDto> toModel(ItemAttributeDto itemAttributeDto) {
        return new EntityModel<>(itemAttributeDto,
                linkTo(methodOn(ItemAttributeController.class).findAttributeInItem(
                        itemAttributeDto.getItemId(),
                        itemAttributeDto.getAttributeId())
                ).withSelfRel(),
                linkTo(methodOn(ItemAttributeController.class).listAttributesByItem(
                        itemAttributeDto.getItemId(),
                        Pageable.unpaged()
                )).withRel("item attributes"),
                linkTo(methodOn(ItemController.class).find(itemAttributeDto.getItemId()))
                        .withRel("item"),
                linkTo(methodOn(AttributeController.class).find(itemAttributeDto.getAttributeId()))
                        .withRel("attribute")
        );
    }
}
