package com.srh.api.service;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class LoginClientDto {
    private final Boolean validUser;
    private Integer userId;
}
