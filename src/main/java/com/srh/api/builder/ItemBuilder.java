package com.srh.api.builder;

import com.srh.api.model.*;

import java.util.List;

public final class ItemBuilder {
    private Integer id;
    private String name;
    private String description;
    private List<ItemRating> itemRatings;
    private List<Recommendation> recommendations;
    private Project project;
    private TypeItem typeItem;
    private List<Tag> tags;
    private List<Attribute> attributes;

    private ItemBuilder() {
    }

    public static ItemBuilder anItem() {
        return new ItemBuilder();
    }

    public ItemBuilder withId(Integer id) {
        this.id = id;
        return this;
    }

    public ItemBuilder withName(String name) {
        this.name = name;
        return this;
    }

    public ItemBuilder withDescription(String description) {
        this.description = description;
        return this;
    }

    public ItemBuilder withItemRatings(List<ItemRating> itemRatings) {
        this.itemRatings = itemRatings;
        return this;
    }

    public ItemBuilder withRecommendations(List<Recommendation> recommendations) {
        this.recommendations = recommendations;
        return this;
    }

    public ItemBuilder withProject(Project project) {
        this.project = project;
        return this;
    }

    public ItemBuilder withTypeItem(TypeItem typeItem) {
        this.typeItem = typeItem;
        return this;
    }

    public ItemBuilder withTags(List<Tag> tags) {
        this.tags = tags;
        return this;
    }

    public ItemBuilder withAttributes(List<Attribute> attributes) {
        this.attributes = attributes;
        return this;
    }

    public Item build() {
        Item item = new Item();
        item.setId(id);
        item.setName(name);
        item.setDescription(description);
        item.setItemRatings(itemRatings);
        item.setRecommendations(recommendations);
        item.setProject(project);
        item.setTypeItem(typeItem);
        item.setTags(tags);
        item.setAttributes(attributes);
        return item;
    }
}
