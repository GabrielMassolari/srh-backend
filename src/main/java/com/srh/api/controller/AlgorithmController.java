package com.srh.api.controller;

import com.srh.api.dto.resource.AlgorithmDto;
import com.srh.api.dto.resource.AlgorithmForm;
import com.srh.api.hypermedia.AlgorithmModelAssembler;
import com.srh.api.model.Algorithm;
import com.srh.api.service.AlgorithmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.transaction.Transactional;
import javax.validation.Valid;

import java.net.URI;

import static com.srh.api.dto.resource.AlgorithmDto.convert;

@RestController
@RequestMapping("/algorithms")
public class AlgorithmController {
    @Autowired
    private AlgorithmService algorithmService;

    @Autowired
    private AlgorithmModelAssembler algorithmModelAssembler;

    @Autowired
    private PagedResourcesAssembler<AlgorithmDto> pagedResourcesAssembler;

    @GetMapping
    public PagedModel<EntityModel<AlgorithmDto>> listAll(@PageableDefault(page = 0, size = 5)
                                                                 Pageable pageInfo) {
        Page<Algorithm> algorithms = algorithmService.findAll(pageInfo);
        return pagedResourcesAssembler.toModel(convert(algorithms));
    }

    @GetMapping("/{id}")
    public EntityModel<AlgorithmDto> find(@PathVariable Integer id) {
        Algorithm algorithm = algorithmService.find(id);
        return algorithmModelAssembler.toModel(new AlgorithmDto(algorithm));
    }

    @PostMapping
    public ResponseEntity<EntityModel<AlgorithmDto>> create(@RequestBody @Valid AlgorithmForm algorithmForm,
                                                            UriComponentsBuilder uriBuilder) {
        Algorithm algorithm = algorithmForm.build();
        algorithmService.save(algorithm);
        URI uri = uriBuilder.path("/algorithms/{id}").buildAndExpand(algorithm.getId()).toUri();
        return ResponseEntity.created(uri)
                .body(algorithmModelAssembler.toModel(new AlgorithmDto(algorithm)));
    }

    @PutMapping("/{id}")
    @Transactional
    public EntityModel<AlgorithmDto> update(@RequestBody @Valid AlgorithmForm algorithmForm,
                                            @PathVariable Integer id) {
        Algorithm algorithm = algorithmForm.build();
        algorithm.setId(id);
        algorithm = algorithmService.update(algorithm);
        return algorithmModelAssembler.toModel(new AlgorithmDto(algorithm));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        algorithmService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
