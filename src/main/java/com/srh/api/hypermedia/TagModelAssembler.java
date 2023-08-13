package com.srh.api.hypermedia;

import com.srh.api.controller.TagController;
import com.srh.api.dto.resource.TagDto;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
public class TagModelAssembler implements RepresentationModelAssembler<TagDto, EntityModel<TagDto>> {

    public EntityModel<TagDto> toModel(TagDto tagDto) {
        return new EntityModel<>(tagDto,
                linkTo(methodOn(TagController.class).find(tagDto.getId())).withSelfRel(),
                linkTo(TagController.class).withRel("tags")
        );
    }
}
