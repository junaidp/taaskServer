package com.echo.taask;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ErrorAdvice {
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(InvalidFormatException.class)
    public Map<String,String> formatExceptionHandler(InvalidFormatException ex)
    {
        Map<String,String> errorMessage = new HashMap<>();
        errorMessage.put("error", ex.getOriginalMessage());
return errorMessage;
    }
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String,String> formatExceptionHandler(MethodArgumentNotValidException ex)
    {
        Map<String,String> errorMessage = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error ->
        {
            errorMessage.put(error.getField(),error.getDefaultMessage());
        });
        return errorMessage;
    }



}
