package com.srh.api.repository;

import com.srh.api.model.Recommendation;
import com.srh.api.model.RecommendationRating;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;

public interface RecommendationRatingRepository extends PagingAndSortingRepository<RecommendationRating, Integer> {

    @Query(value="SELECT * FROM RECOMMENDATION_RATING WHERE recommendation_id = ?1", nativeQuery = true)
    Optional<RecommendationRating> findByRecommendationId(Integer recommendationId);
}
