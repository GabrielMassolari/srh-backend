package com.srh.api.dto.resource;

import com.srh.api.model.ItemRating;
import com.srh.api.model.Rating;
import lombok.Getter;
import org.springframework.data.domain.Page;

import java.time.LocalDateTime;

@Getter
public class RatingDto {
    private final Integer id;
    private final Double score;
    private final LocalDateTime date;

    public RatingDto(Rating rating) {
        this.id = rating.getId();
        this.score = rating.getScore();
        this.date = rating.getDate();
    }
}
