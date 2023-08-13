package com.srh.api.builder;

import com.srh.api.model.ItemRating;
import com.srh.api.model.ItemRatingPK;

import java.time.LocalDateTime;

public final class ItemRatingBuilder {
    private ItemRatingPK id;
    private Double score;
    private LocalDateTime date;

    private ItemRatingBuilder() {
    }

    public static ItemRatingBuilder anItemRating() {
        return new ItemRatingBuilder();
    }

    public ItemRatingBuilder withId(ItemRatingPK id) {
        this.id = id;
        return this;
    }

    public ItemRatingBuilder withScore(Double score) {
        this.score = score;
        return this;
    }

    public ItemRatingBuilder withDate(LocalDateTime date) {
        this.date = date;
        return this;
    }

    public ItemRating build() {
        ItemRating itemRating = new ItemRating();
        itemRating.setId(id);
        itemRating.setScore(score);
        itemRating.setDate(date);
        return itemRating;
    }
}
