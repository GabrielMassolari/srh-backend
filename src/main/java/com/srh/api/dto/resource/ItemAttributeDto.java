package com.srh.api.dto.resource;

import com.srh.api.model.ItemAttribute;
import lombok.Getter;
import org.springframework.hateoas.server.core.Relation;

@Getter
@Relation(collectionRelation = "relationship")
public class ItemAttributeDto {
    private final Integer itemId;
    private final Integer attributeId;

    public ItemAttributeDto(ItemAttribute itemAttribute) {
        this.itemId = itemAttribute.getItem().getId();
        this.attributeId = itemAttribute.getAttribute().getId();
    }
}
