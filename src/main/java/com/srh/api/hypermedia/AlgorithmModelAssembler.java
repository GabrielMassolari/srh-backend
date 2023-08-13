package com.srh.api.hypermedia;

import com.srh.api.controller.AlgorithmController;
import com.srh.api.dto.resource.AlgorithmDto;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class AlgorithmModelAssembler implements RepresentationModelAssembler<AlgorithmDto, EntityModel<AlgorithmDto>> {
    @Override
    public EntityModel<AlgorithmDto> toModel(AlgorithmDto algorithmDto) {
        return new EntityModel<>(algorithmDto,
                linkTo(methodOn(AlgorithmController.class).find(algorithmDto.getId())).withSelfRel(),
                linkTo(methodOn(AlgorithmController.class).listAll(Pageable.unpaged())).withRel("algoritms")
        );
    }
}
