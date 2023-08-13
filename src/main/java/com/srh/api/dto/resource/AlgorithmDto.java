package com.srh.api.dto.resource;

import com.srh.api.model.Algorithm;
import lombok.Getter;
import org.springframework.data.domain.Page;
import org.springframework.hateoas.server.core.Relation;

@Getter
@Relation(collectionRelation = "algorithms")
public class AlgorithmDto {
    private final Integer id;
    private final String name;
    private final String typeRecommendation;

    public AlgorithmDto(Algorithm algorithm) {
        this.id = algorithm.getId();
        this.name = algorithm.getName();
        this.typeRecommendation = algorithm.getTypeRecommendation().toString();
    }

    public static Page<AlgorithmDto> convert(Page<Algorithm> algorithms) {
        return algorithms.map(AlgorithmDto::new);
    }
}
