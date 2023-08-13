package com.srh.api.dto.error;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class DefaultErrorDto {
    private final String error;
    private final String cause;
}
