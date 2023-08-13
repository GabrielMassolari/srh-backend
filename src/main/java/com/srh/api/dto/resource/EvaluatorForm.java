package com.srh.api.dto.resource;

import com.srh.api.builder.EvaluatorBuilder;
import com.srh.api.model.Evaluator;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class EvaluatorForm {
    @NotNull
    @NotEmpty
    @Length(min = 3)
    private String name;

    @NotNull
    @NotEmpty
    @Length(min = 3)
    private String login;

    @NotNull
    @NotEmpty
    @Length(min = 6)
    private String password;

    @NotNull
    @NotEmpty
    @Email
    private String email;

    private String oldPassword;

    public Evaluator build() {
        return EvaluatorBuilder.anEvaluator()
                .withName(name)
                .withLogin(login)
                .withPassword(password)
                .withOldPassword(oldPassword)
                .withEmail(email)
                .build();
    }
}
