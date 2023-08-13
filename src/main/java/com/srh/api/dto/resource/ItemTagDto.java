package com.srh.api.dto.resource;

import com.srh.api.model.ItemTag;
import lombok.Getter;
import org.springframework.hateoas.server.core.Relation;

@Getter
@Relation(collectionRelation = "relationship")
public class ItemTagDto {
    private final Integer itemId;
    private final Integer tagId;

    public ItemTagDto(ItemTag itemTag) {
        this.itemId = itemTag.getItem().getId();
        this.tagId = itemTag.getTag().getId();
    }
}
