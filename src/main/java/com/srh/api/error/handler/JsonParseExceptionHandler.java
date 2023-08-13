package com.srh.api.error.handler;

import com.fasterxml.jackson.core.JsonParseException;
import com.srh.api.dto.error.DefaultErrorDto;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class JsonParseExceptionHandler {
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(JsonParseException.class)
    public DefaultErrorDto handle(Exception exception) {
        return new DefaultErrorDto(
                "Existem erros no corpo de sua requisição",
                exception.getMessage()
        );
    }
}
