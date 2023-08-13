package com.srh.api.dto.resource;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RecommendationForm {
    @Min(value = 0)
    @Max(value = 5)
    @NotNull
    private Double passingScore;

    @NotNull
    private Integer algorithmId;

    @NotNull
    private Integer projectId;

    @Min(value = 0)
    @Max(value = 5)
    private Integer decimalPrecision;
}
