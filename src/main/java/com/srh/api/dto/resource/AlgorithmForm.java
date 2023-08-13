package com.srh.api.dto.resource;

import com.srh.api.builder.AlgorithmBuilder;
import com.srh.api.model.Algorithm;
import com.srh.api.model.TypeRecommendation;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class AlgorithmForm {
    @NotNull
    @NotEmpty
    private String name;

    @NotNull
    @NotEmpty
    private String typeRecommendation;

    public Algorithm build() {
        return AlgorithmBuilder.anAlgorithm()
                .withName(name)
                .withTypeRecommendation(TypeRecommendation.valueOf(typeRecommendation))
                .build();
    }
}
