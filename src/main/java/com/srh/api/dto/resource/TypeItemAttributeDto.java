package com.srh.api.dto.resource;

import com.srh.api.model.TypeItemAttribute;
import lombok.Getter;
import org.springframework.hateoas.server.core.Relation;

@Getter
@Relation(collectionRelation = "relationship")
public class TypeItemAttributeDto {
    private final Integer typeItemId;
    private final Integer attributeId;

    public TypeItemAttributeDto(TypeItemAttribute typeItemAttribute) {
        this.typeItemId = typeItemAttribute.getTypeItem().getId();
        this.attributeId = typeItemAttribute.getAttribute().getId();
    }
}
