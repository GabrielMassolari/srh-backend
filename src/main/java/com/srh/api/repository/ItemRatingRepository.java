package com.srh.api.repository;

import com.srh.api.model.ItemRating;
import com.srh.api.model.ItemRatingPK;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

public interface ItemRatingRepository extends PagingAndSortingRepository<ItemRating, ItemRatingPK> {
    Optional<ItemRating> findById(ItemRatingPK itemRatingPK);

    @Query("SELECT * FROM ITEM_RATING WHERE evaluator_id = ?1 AND item_id = ?2")
    Optional<ItemRating> findByEvaluatorAndItem(Integer evaluatorId, Integer itemId);
}
