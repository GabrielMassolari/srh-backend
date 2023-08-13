package com.srh.api.dto.resource;

import com.srh.api.builder.TagBuilder;
import com.srh.api.model.Tag;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class TagForm {
    @NotEmpty
    @NotNull
    private String name;

    public Tag build() {
        return TagBuilder.aTag()
                .withName(name)
                .build();
    }
}
