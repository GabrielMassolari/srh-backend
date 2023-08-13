package com.srh.api.dto.resource;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ProjectEvaluatorForm {
    @NotNull
    private Integer projectId;
    @NotNull
    private Integer evaluatorId;
}
