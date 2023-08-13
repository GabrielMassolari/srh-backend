package com.srh.api.builder;

import com.srh.api.model.Item;
import com.srh.api.model.Tag;

import java.util.List;

public final class TagBuilder {
    private Integer id;
    private String name;
    private List<Item> itens;

    private TagBuilder() {
    }

    public static TagBuilder aTag() {
        return new TagBuilder();
    }

    public TagBuilder withId(Integer id) {
        this.id = id;
        return this;
    }

    public TagBuilder withName(String name) {
        this.name = name;
        return this;
    }

    public TagBuilder withItens(List<Item> itens) {
        this.itens = itens;
        return this;
    }

    public Tag build() {
        Tag tag = new Tag();
        tag.setId(id);
        tag.setName(name);
        tag.setItens(itens);
        return tag;
    }
}
