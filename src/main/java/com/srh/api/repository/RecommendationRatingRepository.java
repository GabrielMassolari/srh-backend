package com.srh.api.repository;

import com.srh.api.model.RecommendationRating;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface RecommendationRatingRepository extends PagingAndSortingRepository<RecommendationRating, Integer> {
}
