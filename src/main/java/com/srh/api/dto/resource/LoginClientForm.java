package com.srh.api.dto.resource;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

@Getter
@AllArgsConstructor
@NoArgsConstructor
public class LoginClientForm {
    @NotNull
    @NotEmpty
    @Length(min = 3)
    private String login;

    @NotNull
    @NotEmpty
    @Length(min = 6)
    private String password;
}
