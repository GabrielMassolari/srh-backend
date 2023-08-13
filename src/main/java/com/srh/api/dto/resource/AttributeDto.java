package com.srh.api.dto.resource;

import com.srh.api.model.Attribute;
import lombok.Getter;
import org.springframework.data.domain.Page;
import org.springframework.hateoas.server.core.Relation;

@Getter
@Relation(collectionRelation = "attributes")
public class AttributeDto {
    private final Integer id;
    private final String name;
    private final String value;
    private final String type;

    public AttributeDto(Attribute attribute) {
        this.id = attribute.getId();
        this.name = attribute.getName();
        this.value = attribute.getValue();
        this.type = attribute.getType().toString();
    }

    public static Page<AttributeDto> convert(Page<Attribute> attributes) {
        return attributes.map(AttributeDto::new);
    }
}
