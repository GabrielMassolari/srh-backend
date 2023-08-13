package com.srh.api.error.handler;

import com.srh.api.dto.error.DefaultErrorDto;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class IllegalArgumentHandler {
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(IllegalArgumentException.class)
    public DefaultErrorDto handle(Exception exception) {
        return new DefaultErrorDto(
                "Campos informados com valores inválidos",
                exception.getMessage()
        );
    }
}
