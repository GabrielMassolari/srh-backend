package com.srh.api.error.handler;

import com.srh.api.dto.error.DefaultErrorDto;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.persistence.EntityNotFoundException;

@RestControllerAdvice
public class EntityNotFoundHandler {
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(EntityNotFoundException.class)
    public DefaultErrorDto handle(Exception exception) {
        return new DefaultErrorDto(
                "Um dos dados informados não existe",
                exception.getMessage()
        );
    }
}
