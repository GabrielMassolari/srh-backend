package com.srh.api.dto.resource;

import com.srh.api.model.ItemRating;
import lombok.Getter;
import org.springframework.data.domain.Page;
import org.springframework.hateoas.server.core.Relation;

@Getter
@Relation(collectionRelation = "itemRatings")
public class ItemRatingDto {
    private final Integer evaluatorId;
    private final Integer itemId;
    private final Double score;

    public ItemRatingDto(ItemRating itemRating) {
        this.evaluatorId = itemRating.getId().getEvaluator().getId();
        this.itemId = itemRating.getId().getItem().getId();
        this.score = itemRating.getScore();
    }

    public static Page<ItemRatingDto> convert(Page<ItemRating> itemRatings) {
        return itemRatings.map(ItemRatingDto::new);
    }
}
