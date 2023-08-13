package com.srh.api.controller;

import com.srh.api.dto.resource.TagDto;
import com.srh.api.dto.resource.TagForm;
import com.srh.api.dto.resource.TypeItemDto;
import com.srh.api.hypermedia.TagModelAssembler;
import com.srh.api.model.Tag;
import com.srh.api.service.TagService;
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

import static com.srh.api.dto.resource.TagDto.*;

@RestController
@RequestMapping("/tags")
public class TagController {
    @Autowired
    private TagService tagService;

    @Autowired
    private TagModelAssembler tagModelAssembler;

    @Autowired
    private PagedResourcesAssembler<TagDto> pagedResourcesAssembler;

    @GetMapping
    public PagedModel<EntityModel<TagDto>> listAll(@PageableDefault(page = 0, size = 5) Pageable pageInfo) {
        Page<Tag> tags = tagService.findAll(pageInfo);
        return pagedResourcesAssembler.toModel(convert(tags));
    }

    @GetMapping("/{id}")
    public EntityModel<TagDto> find(@PathVariable Integer id) {
        Tag tag = tagService.find(id);
        return tagModelAssembler.toModel(new TagDto(tag));
    }

    @PostMapping
    public ResponseEntity<EntityModel<TagDto>> create(@RequestBody @Valid TagForm tagForm,
                                                      UriComponentsBuilder uriBuilder) {
        Tag tag = tagForm.build();
        tagService.save(tag);
        URI uri = uriBuilder.path("/tags/{id}").buildAndExpand(tag.getId()).toUri();
        return ResponseEntity.created(uri)
                .body(tagModelAssembler.toModel(new TagDto(tag)));
    }

    @PutMapping("/{id}")
    @Transactional
    public EntityModel<TagDto> update(@RequestBody @Valid TagForm tagForm, @PathVariable Integer id) {
        Tag tag = tagForm.build();
        tag.setId(id);
        tag = tagService.update(tag);
        return tagModelAssembler.toModel(new TagDto(tag));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        tagService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/projects/{projectId}")
    public PagedModel<EntityModel<TagDto>> listByProject(
            @PathVariable Integer projectId,
            @PageableDefault(page = 0, size = 5) Pageable pageInfo
    ) {
        Page<Tag> tags = tagService.listTagsByProject(projectId, pageInfo);
        return pagedResourcesAssembler.toModel(TagDto.convert(tags));
    }
}
