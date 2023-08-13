package com.srh.api.service;

import com.srh.api.model.RecommendationRating;
import com.srh.api.repository.RecommendationRatingRepository;
import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RecommmendationRatingService {
    @Autowired
    private RecommendationRatingRepository recommendationRatingRepository;

    public RecommendationRating find(Integer id) {
        Optional<RecommendationRating> recommendationRating = recommendationRatingRepository.findById(id);

        if (recommendationRating.isPresent())
            return recommendationRating.get();

        throw new ObjectNotFoundException(id, RecommendationRating.class.getName());
    }

    public Page<RecommendationRating> findAll(Pageable pageInfo) {
        return recommendationRatingRepository.findAll(pageInfo);
    }

    public RecommendationRating save(RecommendationRating recommendationRating) {
        return recommendationRatingRepository.save(recommendationRating);
    }

    public RecommendationRating update(RecommendationRating recommendationRating) {
        find(recommendationRating.getId());
        return recommendationRatingRepository.save(recommendationRating);
    }

    public void delete(Integer id) {
        find(id);
        recommendationRatingRepository.deleteById(id);
    }
}
