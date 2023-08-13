package com.srh.api.dto.resource;

import com.srh.api.builder.EvaluatorBuilder;
import com.srh.api.builder.ItemBuilder;
import com.srh.api.builder.ItemRatingBuilder;
import com.srh.api.builder.ItemRatingPKBuilder;
import com.srh.api.model.Evaluator;
import com.srh.api.model.Item;
import com.srh.api.model.ItemRating;
import com.srh.api.model.ItemRatingPK;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ItemRatingForm {
    @NotNull
    private Double score;
    @NotNull
    private Integer evaluatorId;
    @NotNull
    private Integer itemId;

    public ItemRating build() {
        Evaluator evaluator = EvaluatorBuilder.anEvaluator()
                .withId(evaluatorId)
                .build();

        Item item = ItemBuilder.anItem()
                .withId(itemId)
                .build();

        ItemRatingPK itemRatingPK = ItemRatingPKBuilder.anItemRatingPK()
                .withItem(item)
                .withEvaluator(evaluator)
                .build();

        return ItemRatingBuilder.anItemRating()
                .withScore(score)
                .withId(itemRatingPK)
                .withDate(LocalDateTime.now())
                .build();
    }
}
