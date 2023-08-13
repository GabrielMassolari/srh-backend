package com.srh.api.builder;

import com.srh.api.model.Item;
import com.srh.api.model.ItemTag;
import com.srh.api.model.Tag;

public final class ItemTagBuilder {
    private Item item;
    private Tag tag;

    private ItemTagBuilder() {
    }

    public static ItemTagBuilder anItemTag() {
        return new ItemTagBuilder();
    }

    public ItemTagBuilder withItem(Item item) {
        this.item = item;
        return this;
    }

    public ItemTagBuilder withTag(Tag tag) {
        this.tag = tag;
        return this;
    }

    public ItemTag build() {
        return new ItemTag(item, tag);
    }
}
