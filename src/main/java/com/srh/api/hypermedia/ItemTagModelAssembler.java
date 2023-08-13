package com.srh.api.hypermedia;

import com.srh.api.controller.ItemController;
import com.srh.api.controller.ItemTagController;
import com.srh.api.controller.TagController;
import com.srh.api.dto.resource.ItemTagDto;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class ItemTagModelAssembler implements RepresentationModelAssembler<ItemTagDto, EntityModel<ItemTagDto>> {
    @Override
    public EntityModel<ItemTagDto> toModel(ItemTagDto itemTagDto) {
        return new EntityModel<>(itemTagDto,
                linkTo(methodOn(ItemTagController.class).findTagByItem(
                        itemTagDto.getItemId(), itemTagDto.getTagId()
                )).withSelfRel(),
                linkTo(methodOn(ItemTagController.class).listTagsByItem(
                        itemTagDto.getItemId(), Pageable.unpaged()
                )).withRel("tags in item"),
                linkTo(methodOn(ItemController.class).find(itemTagDto.getItemId()))
                        .withRel("item"),
                linkTo(methodOn(TagController.class).find(itemTagDto.getTagId()))
                        .withRel("tag")
        );
    }
}
