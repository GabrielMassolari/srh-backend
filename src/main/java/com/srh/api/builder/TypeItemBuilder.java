package com.srh.api.builder;

import com.srh.api.model.Attribute;
import com.srh.api.model.Item;
import com.srh.api.model.TypeItem;

import java.util.List;

public final class TypeItemBuilder {
    private Integer id;
    private String name;
    private List<Item> itens;
    private List<Attribute> attributes;

    private TypeItemBuilder() {
    }

    public static TypeItemBuilder aTypeItem() {
        return new TypeItemBuilder();
    }

    public TypeItemBuilder withId(Integer id) {
        this.id = id;
        return this;
    }

    public TypeItemBuilder withName(String name) {
        this.name = name;
        return this;
    }

    public TypeItemBuilder withItens(List<Item> itens) {
        this.itens = itens;
        return this;
    }

    public TypeItemBuilder withAttributes(List<Attribute> attributes) {
        this.attributes = attributes;
        return this;
    }

    public TypeItem build() {
        TypeItem typeItem = new TypeItem();
        typeItem.setId(id);
        typeItem.setName(name);
        typeItem.setItens(itens);
        typeItem.setAttributes(attributes);
        return typeItem;
    }
}
