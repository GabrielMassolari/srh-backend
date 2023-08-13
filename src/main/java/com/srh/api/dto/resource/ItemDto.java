package com.srh.api.dto.resource;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.srh.api.model.Item;
import com.srh.api.model.Project;
import com.srh.api.model.TypeItem;
import lombok.Getter;
import org.springframework.data.domain.Page;
import org.springframework.hateoas.server.core.Relation;

@Getter
@Relation(collectionRelation = "itens")
public class ItemDto {
    private final Integer id;
    private final String name;
    private final String description;
    private Integer typeItemId;

    @JsonIgnore
    private final Project project;
    @JsonIgnore
    private final TypeItem typeItem;

    public ItemDto(Item item) {
        this.id = item.getId();
        this.name = item.getName();
        this.description = item.getDescription();
        this.project = item.getProject();
        this.typeItem = item.getTypeItem();

        if (item.getTypeItem() != null) {
            this.typeItemId = item.getTypeItem().getId();
        }
    }

    public static Page<ItemDto> convert(Page<Item> items) {
        return items.map(ItemDto::new);
    }
}
