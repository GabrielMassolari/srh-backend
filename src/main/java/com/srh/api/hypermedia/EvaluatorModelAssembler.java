package com.srh.api.hypermedia;

import com.srh.api.controller.EvaluatorController;
import com.srh.api.dto.resource.EvaluatorDto;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class EvaluatorModelAssembler implements RepresentationModelAssembler<EvaluatorDto, EntityModel<EvaluatorDto>> {
    @Override
    public EntityModel<EvaluatorDto> toModel(EvaluatorDto evaluatorDto) {
        return new EntityModel<>(evaluatorDto,
                linkTo(methodOn(EvaluatorController.class).find(evaluatorDto.getId())).withSelfRel(),
                linkTo(EvaluatorController.class).withRel("evaluators")
        );
    }
}
