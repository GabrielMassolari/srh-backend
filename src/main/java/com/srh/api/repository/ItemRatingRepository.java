package com.srh.api.repository;

import com.srh.api.model.ItemRating;
import com.srh.api.model.ItemRatingPK;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

public interface ItemRatingRepository extends PagingAndSortingRepository<ItemRating, ItemRatingPK> {
    Optional<ItemRating> findById(ItemRatingPK itemRatingPK);
}
