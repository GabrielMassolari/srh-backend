package com.srh.api.dto.resource;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ItemTagForm {
    @NotNull
    private Integer itemId;
    @NotNull
    private Integer tagId;
}
