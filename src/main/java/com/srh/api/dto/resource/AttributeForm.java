package com.srh.api.dto.resource;

import com.srh.api.builder.AttributeBuilder;
import com.srh.api.model.Attribute;
import com.srh.api.model.TypeAttribute;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class AttributeForm {
    @NotNull
    @NotEmpty
    private String name;

    @NotNull
    @NotEmpty
    private String value;

    @NotNull
    @NotEmpty
    private String type;

    public Attribute build() {
        return AttributeBuilder.anAttribute()
                .withName(name)
                .withValue(value)
                .withType(TypeAttribute.valueOf(type))
                .build();
    }
}
