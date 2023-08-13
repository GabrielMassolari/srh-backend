package com.srh.api.builder;

import com.srh.api.model.Evaluator;
import com.srh.api.model.Item;
import com.srh.api.model.ItemRatingPK;

public final class ItemRatingPKBuilder {
    private Item item;
    private Evaluator evaluator;

    private ItemRatingPKBuilder() {
    }

    public static ItemRatingPKBuilder anItemRatingPK() {
        return new ItemRatingPKBuilder();
    }

    public ItemRatingPKBuilder withItem(Item item) {
        this.item = item;
        return this;
    }

    public ItemRatingPKBuilder withEvaluator(Evaluator evaluator) {
        this.evaluator = evaluator;
        return this;
    }

    public ItemRatingPK build() {
        ItemRatingPK itemRatingPK = new ItemRatingPK();
        itemRatingPK.setItem(item);
        itemRatingPK.setEvaluator(evaluator);
        return itemRatingPK;
    }
}
