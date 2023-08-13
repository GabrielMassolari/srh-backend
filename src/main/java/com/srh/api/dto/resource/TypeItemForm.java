package com.srh.api.dto.resource;

import com.srh.api.builder.TypeItemBuilder;
import com.srh.api.model.TypeItem;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class TypeItemForm {
    @NotNull
    @NotEmpty
    private String name;

    public TypeItem build() {
        return TypeItemBuilder.aTypeItem()
                .withName(name)
                .build();
    }
}
