package com.srh.api.controller;

import com.srh.api.dto.resource.*;
import com.srh.api.hypermedia.ProjectModelAssembler;
import com.srh.api.model.*;
import com.srh.api.service.ProjectService;
import com.srh.api.utils.PageUtil;
import lombok.SneakyThrows;
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

import static com.srh.api.dto.resource.ProjectDto.convert;

@RestController
@RequestMapping("/projects")
public class ProjectController {
    @Autowired
    private ProjectService projectService;

    @Autowired
    private ProjectModelAssembler projectModelAssembler;

    @Autowired
    private PagedResourcesAssembler<ProjectDto> pagedResourcesAssembler;

    @Autowired
    private PagedResourcesAssembler<ItemDto> itemDtoPagedResourcesAssembler;

    @Autowired
    private PagedResourcesAssembler<RecommendationDto> recommendationDtoPagedResourcesAssembler;

    @Autowired
    private PagedResourcesAssembler<ItemRatingDto> itemRatingDtoPagedResourcesAssembler;

    @Autowired
    private PagedResourcesAssembler<TagDto> tagDtoPagedResourcesAssembler;

    @Autowired
    private PagedResourcesAssembler<TypeItemDto> typeItemDtoPagedResourcesAssembler;

    @GetMapping
    public PagedModel<EntityModel<ProjectDto>> listAll(@PageableDefault(page = 0, size = 5)
                                                               Pageable pageInfo) {
        Page<Project> projects = projectService.findAll(pageInfo);
        return pagedResourcesAssembler.toModel(convert(projects));
    }

    @GetMapping("/{id}")
    public EntityModel<ProjectDto> find(@PathVariable Integer id) {
        Project project = projectService.find(id);
        return projectModelAssembler.toModel(new ProjectDto(project));
    }

    @PostMapping
    @SneakyThrows
    public ResponseEntity<EntityModel<ProjectDto>> create(@RequestBody @Valid ProjectForm projectForm,
                                                          UriComponentsBuilder uriBuilder) {
        Project project = projectForm.build();
        Project projectPersist = projectService.save(project);
        URI uri = uriBuilder.path("/projects/{id}").buildAndExpand(project.getId()).toUri();
        return ResponseEntity.created(uri)
                .body(projectModelAssembler.toModel(new ProjectDto(projectPersist)));
    }

    @PutMapping("/{id}")
    @Transactional
    @SneakyThrows
    public EntityModel<ProjectDto> update(@RequestBody @Valid ProjectForm projectForm,
                                          @PathVariable Integer id) {
        Project project = projectForm.build();
        project.setId(id);
        project = projectService.update(project);
        return projectModelAssembler.toModel(new ProjectDto(project));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        projectService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{projectId}/itens")
    public PagedModel<EntityModel<ItemDto>> findItensByProject(
            @PathVariable Integer projectId,
            @PageableDefault(page = 0, size = 5) Pageable pageInfo
    ) {
        PageUtil<Item> pageUtil = new PageUtil<>(pageInfo, projectService.
                listItensByProject(projectId));

        return itemDtoPagedResourcesAssembler.toModel(ItemDto.convert(pageUtil.getPage()));
    }

    @GetMapping("{projectId}/recommendations")
    public PagedModel<EntityModel<RecommendationDto>> findRecommendationsByProject(
            @PathVariable Integer projectId,
            @PageableDefault(page = 0, size = 5) Pageable pageInfo
    ) {
        PageUtil<Recommendation> pageUtil = new PageUtil<>(pageInfo, projectService.
                listRecommendationsByProject(projectId));

        return recommendationDtoPagedResourcesAssembler.toModel(RecommendationDto.convert(
                pageUtil.getPage()
        ));
    }

    @GetMapping("{projectId}/itemratings")
    public PagedModel<EntityModel<ItemRatingDto>> findItemRatingsByProject(
            @PathVariable Integer projectId,
            @PageableDefault(page = 0, size = 5) Pageable pageInfo
    ) {
        PageUtil<ItemRating> pageUtil = new PageUtil<>(pageInfo, projectService.
                listItemRatingsByProject(projectId));

        return itemRatingDtoPagedResourcesAssembler.toModel(ItemRatingDto.convert(
                pageUtil.getPage()
        ));
    }

    @GetMapping("{projectId}/tags")
    public PagedModel<EntityModel<TagDto>> findTagsByProject(
            @PathVariable Integer projectId,
            @PageableDefault(page = 0, size = 5) Pageable pageInfo
    ) {
        PageUtil<Tag> pageUtil = new PageUtil<>(pageInfo, projectService.
                listTagsByProject(projectId));

        return tagDtoPagedResourcesAssembler.toModel(TagDto.convert(
                pageUtil.getPage()
        ));
    }

    @GetMapping("{projectId}/typeitens")
    public PagedModel<EntityModel<TypeItemDto>> findTypeItensByProject(
            @PathVariable Integer projectId,
            @PageableDefault(page = 0, size = 5) Pageable pageInfo
    ) {
        PageUtil<TypeItem> pageUtil = new PageUtil<>(pageInfo, projectService.
                listTypeItensByProject(projectId));

        return typeItemDtoPagedResourcesAssembler.toModel(TypeItemDto.convert(
                pageUtil.getPage()
        ));
    }
}
