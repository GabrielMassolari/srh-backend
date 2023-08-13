package com.srh.api.controller;

import com.srh.api.dto.resource.ItemTagDto;
import com.srh.api.dto.resource.ItemTagForm;
import com.srh.api.dto.resource.TagDto;
import com.srh.api.hypermedia.ItemTagModelAssembler;
import com.srh.api.hypermedia.TagModelAssembler;
import com.srh.api.model.ItemTag;
import com.srh.api.model.Tag;
import com.srh.api.service.ItemTagService;
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

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("/itens/{itemId}/tags")
public class ItemTagController {
    @Autowired
    private ItemTagService itemTagService;

    @Autowired
    private ItemTagModelAssembler itemTagModelAssembler;

    @Autowired
    private TagModelAssembler tagModelAssembler;

    @Autowired
    PagedResourcesAssembler<TagDto> pagedResourcesAssembler;

    @GetMapping
    public PagedModel<EntityModel<TagDto>> listTagsByItem(
            @PathVariable Integer itemId, @PageableDefault(page = 0, size = 5) Pageable pageInfo
    ) {
        Page<Tag> tags = itemTagService.listTagsByItem(itemId, pageInfo);
        return pagedResourcesAssembler.toModel(TagDto.convert(tags));
    }

    @GetMapping("/{tagId}")
    public EntityModel<TagDto> findTagByItem(@PathVariable Integer itemId,
                                             @PathVariable Integer tagId) {
        Tag tag = itemTagService.findTagByItem(itemId, tagId);
        return tagModelAssembler.toModel(new TagDto(tag));
    }

    @PostMapping
    @SneakyThrows
    public ResponseEntity<EntityModel<ItemTagDto>> linksTagsItem(
            @PathVariable Integer itemId,
            @RequestBody @Valid ItemTagForm itemTagForm,
            UriComponentsBuilder uriBuilder
    ) {
        ItemTag itemTag = itemTagService.save(
                itemTagForm.getItemId(),
                itemTagForm.getTagId()
        );

        URI uri = uriBuilder.path("/itens/{itemId}/tags/{tagId}")
                .buildAndExpand(itemTag.getItem().getId(), itemTag.getTag().getId())
                .toUri();

        return ResponseEntity.created(uri)
                .body(itemTagModelAssembler.toModel(
                        new ItemTagDto(itemTag)
                ));
    }

    @DeleteMapping("/{tagId}")
    @SneakyThrows
    public ResponseEntity<Void> delete(@PathVariable Integer itemId,
                                       @PathVariable Integer tagId) {
        itemTagService.delete(itemId, tagId);
        return ResponseEntity.noContent().build();
    }
}
