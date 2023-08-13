package com.srh.api.model;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
public class Algorithm {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private TypeRecommendation typeRecommendation;

    @OneToMany(mappedBy = "algorithm")
    private List<Recommendation> recommendations;
}
