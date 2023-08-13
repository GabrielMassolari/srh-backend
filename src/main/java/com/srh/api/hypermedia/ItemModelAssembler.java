package com.srh.api.hypermedia;

import com.srh.api.controller.*;
import com.srh.api.dto.resource.ItemDto;
import com.srh.api.model.TypeItem;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class ItemModelAssembler implements RepresentationModelAssembler<ItemDto, EntityModel<ItemDto>> {
    @Override
    public EntityModel<ItemDto> toModel(ItemDto itemDto) {
        EntityModel<ItemDto> itemEntityModel = new EntityModel<>(itemDto,
                linkTo(methodOn(ItemController.class).find(itemDto.getId())).withSelfRel(),
                linkTo(ItemController.class).withRel("itens"),
                linkTo(methodOn(ProjectController.class).find(itemDto.getProject().getId())).withRel("project"),
                linkTo(methodOn(ItemAttributeController.class).listAttributesByItem(itemDto.getId(), Pageable.unpaged()))
                        .withRel("attributes"),
                linkTo(methodOn(ItemTagController.class).listTagsByItem(itemDto.getId(), Pageable.unpaged())).withRel("tags"),
                linkTo(methodOn(ItemController.class).listRecommendationsByItem(itemDto.getId(), Pageable.unpaged()))
                        .withRel("recommendations")
        );

        if (itemDto.getTypeItem() != null) {
            itemEntityModel.add(buildTypeItemLink(itemDto.getTypeItem()));
        }

        return itemEntityModel;
    }

    private Link buildTypeItemLink(TypeItem typeItem) {
        return linkTo(methodOn(TypeItemController.class).find(typeItem.getId())).withRel("typeitem");
    }
}
