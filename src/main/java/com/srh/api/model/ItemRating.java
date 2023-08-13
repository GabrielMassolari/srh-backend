package com.srh.api.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Data
public class ItemRating implements Serializable {
    private static final long serialVersionUID = 1L;

    @EmbeddedId
    private ItemRatingPK id = new ItemRatingPK();

    private Double score;
    private LocalDateTime date;
}
