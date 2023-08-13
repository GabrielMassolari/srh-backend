package com.srh.api.dto.resource;

import com.srh.api.builder.ItemBuilder;
import com.srh.api.builder.ProjectBuilder;
import com.srh.api.builder.TypeItemBuilder;
import com.srh.api.model.Item;
import com.srh.api.model.Project;
import com.srh.api.model.TypeItem;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ItemForm {
    @NotNull
    @NotEmpty
    @Length(min = 3)
    private String name;

    @NotNull
    @NotEmpty
    @Length(min = 3)
    private String description;

    @NotNull
    private Integer projectId;

    private Integer typeItemId;

    public Item build() {
        Project project = ProjectBuilder.aProject()
                .withId(projectId)
                .build();

        if (typeItemId != null) {
            TypeItem typeItem = TypeItemBuilder.aTypeItem()
                    .withId(typeItemId)
                    .build();

            return ItemBuilder.anItem()
                    .withName(name)
                    .withDescription(description)
                    .withProject(project)
                    .withTypeItem(typeItem)
                    .build();
        }

        return ItemBuilder.anItem()
                .withName(name)
                .withDescription(description)
                .withProject(project)
                .build();
    }
}
