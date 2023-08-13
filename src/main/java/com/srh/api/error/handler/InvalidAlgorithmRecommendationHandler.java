package com.srh.api.error.handler;

import com.srh.api.dto.error.DefaultErrorDto;
import com.srh.api.error.exception.InvalidAlgorithmRecommendationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class InvalidAlgorithmRecommendationHandler {
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(InvalidAlgorithmRecommendationException.class)
    public DefaultErrorDto handle(Exception exception) {
        return new DefaultErrorDto(
                "Algoritmo inválido",
                "O id de algoritmo enviado não foi encontrado no sistema"
        );
    }
}
