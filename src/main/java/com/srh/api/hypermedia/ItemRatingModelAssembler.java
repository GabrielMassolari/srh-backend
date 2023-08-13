package com.srh.api.hypermedia;

import com.srh.api.controller.EvaluatorController;
import com.srh.api.controller.ItemController;
import com.srh.api.controller.ItemRatingController;
import com.srh.api.dto.resource.ItemRatingDto;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class ItemRatingModelAssembler implements RepresentationModelAssembler<ItemRatingDto, EntityModel<ItemRatingDto>> {
    @Override
    public EntityModel<ItemRatingDto> toModel(ItemRatingDto itemRatingDto) {
        return new EntityModel<>(itemRatingDto,
                linkTo(methodOn(ItemRatingController.class)
                        .find(itemRatingDto.getItemId(), itemRatingDto.getEvaluatorId())).withSelfRel(),
                linkTo(methodOn(ItemRatingController.class).listAll(Pageable.unpaged())).withRel("item ratings"),
                linkTo(methodOn(ItemController.class).find(itemRatingDto.getItemId())).withRel("item"),
                linkTo(methodOn(EvaluatorController.class).find(itemRatingDto.getEvaluatorId())).withRel("evaluator")
        );
    }
}
