package com.srh.api.error.handler;

import com.srh.api.dto.error.DefaultErrorDto;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ConstraintViolationHandler {
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(ConstraintViolationException.class)
    public DefaultErrorDto handle(Exception exception) {
        return new DefaultErrorDto(
                "Dados inválidos tentaram ser inseridos na base de dados",
                "Veja se campos como login ou outros com validações não possuem problema"
        );
    }
}
