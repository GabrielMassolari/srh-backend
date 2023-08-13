package com.srh.api.error.handler;

import com.srh.api.dto.error.DefaultErrorDto;
import com.srh.api.error.exception.ChangeRootRelationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ChangeRootRelationHandler {
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ChangeRootRelationException.class)
    public DefaultErrorDto handle(Exception exception) {
        return new DefaultErrorDto(
                "Não é possível alterar a relação entre os recursos",
                exception.getMessage()
        );
    }
}
