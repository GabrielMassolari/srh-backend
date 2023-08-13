package com.srh.api.error.handler;

import com.srh.api.dto.error.DefaultErrorDto;
import com.srh.api.error.exception.NotEqualsPasswordException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class NotEqualsPasswordHandler {
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(NotEqualsPasswordException.class)
    public DefaultErrorDto handle(Exception exception) {
        return new DefaultErrorDto(
                "A senha informada não confere com a registrada no sistema",
                exception.getMessage()
        );
    }
}
