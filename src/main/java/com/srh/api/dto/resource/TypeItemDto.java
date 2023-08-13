package com.srh.api.dto.resource;

import com.srh.api.model.TypeItem;
import lombok.Getter;
import org.springframework.data.domain.Page;
import org.springframework.hateoas.server.core.Relation;

@Getter
@Relation(collectionRelation = "typeitems")
public class TypeItemDto {
    private final Integer id;
    private final String name;

    public TypeItemDto(TypeItem typeItem) {
        this.id = typeItem.getId();
        this.name = typeItem.getName();
    }

    public static Page<TypeItemDto> convert(Page<TypeItem> typeItems) {
        return typeItems.map(TypeItemDto::new);
    }
}
