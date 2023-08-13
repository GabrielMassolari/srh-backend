package com.srh.api.builder;

import com.srh.api.model.Attribute;
import com.srh.api.model.Item;
import com.srh.api.model.TypeAttribute;
import com.srh.api.model.TypeItem;

import java.util.List;

public final class AttributeBuilder {
    private Integer id;
    private String name;
    private String value;
    private TypeAttribute type;
    private List<Item> itens;
    private List<TypeItem> typeItens;

    private AttributeBuilder() {
    }

    public static AttributeBuilder anAttribute() {
        return new AttributeBuilder();
    }

    public AttributeBuilder withId(Integer id) {
        this.id = id;
        return this;
    }

    public AttributeBuilder withName(String name) {
        this.name = name;
        return this;
    }

    public AttributeBuilder withValue(String value) {
        this.value = value;
        return this;
    }

    public AttributeBuilder withType(TypeAttribute type) {
        this.type = type;
        return this;
    }

    public AttributeBuilder withItens(List<Item> itens) {
        this.itens = itens;
        return this;
    }

    public AttributeBuilder withTypeItens(List<TypeItem> typeItens) {
        this.typeItens = typeItens;
        return this;
    }

    public Attribute build() {
        Attribute attribute = new Attribute();
        attribute.setId(id);
        attribute.setName(name);
        attribute.setValue(value);
        attribute.setType(type);
        attribute.setItens(itens);
        attribute.setTypeItens(typeItens);
        return attribute;
    }
}
