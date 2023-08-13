package com.srh.api.error.handler;

import com.srh.api.dto.error.DefaultErrorDto;
import com.srh.api.error.exception.OldPasswordNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class OldPasswordNotFound {
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(OldPasswordNotFoundException.class)
    public DefaultErrorDto handle(Exception exception) {
        return new DefaultErrorDto(
                "A senha antiga não foi informada",
                exception.getMessage()
        );
    }
}
