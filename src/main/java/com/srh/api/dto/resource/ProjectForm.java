package com.srh.api.dto.resource;

import com.srh.api.builder.AdminBuilder;
import com.srh.api.builder.ProjectBuilder;
import com.srh.api.model.Admin;
import com.srh.api.model.Project;
import com.srh.api.model.Situations;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ProjectForm {

    @NotEmpty
    @NotNull
    @Length(min = 3)
    private String name;

    @NotEmpty
    @NotNull
    @Length(min = 3)
    private String description;

    @NotNull
    private Integer adminId;

    @NotNull
    @NotEmpty
    private String situation;

    @NotNull
    private Boolean visible;

    public Project build() {
        Admin admin = AdminBuilder.anAdmin()
                .withId(adminId)
                .build();

        return ProjectBuilder.aProject()
                .withName(name)
                .withDescription(description)
                .withAdmin(admin)
                .withSituation(Situations.valueOf(situation))
                .withDate(LocalDate.now())
                .withVisible(visible)
                .withLastMatrixId(0)
                .build();
    }
}
