package com.srh.api.error.handler;

import com.srh.api.dto.error.DefaultErrorDto;
import com.srh.api.error.exception.ProjectNotOpenedException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ProjectNotOpenedHandler {
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ProjectNotOpenedException.class)
    public DefaultErrorDto handle(Exception exception) {
        return new DefaultErrorDto(
                "O projeto não está disponível para inserções",
                exception.getMessage()
        );
    }
}
