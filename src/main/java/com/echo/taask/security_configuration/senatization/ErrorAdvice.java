package com.echo.taask.security_configuration.senatization;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.multipart.MultipartException;
import org.springframework.web.multipart.support.MissingServletRequestPartException;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ErrorAdvice {
    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<String> handleMissingServletRequestParameterException(MissingServletRequestParameterException ex) {
        String paramName = ex.getParameterName();
        String errorMessage = "Required request parameter '" + paramName + "' is missing.";
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessage);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(InvalidFormatException.class)
    public Map<String, String> formatExceptionHandler(InvalidFormatException ex) {
        Map<String, String> errorMessage = new HashMap<>();
        errorMessage.put("error", ex.getOriginalMessage());
        return errorMessage;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> MethodArgumentNotValidExceptionHandler(MethodArgumentNotValidException ex) {
        Map<String, String> errorMessage = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error ->
        {
            errorMessage.put(error.getField(), error.getDefaultMessage());
        });
        return errorMessage;
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MissingServletRequestPartException.class)
    public Map<String, String> MissingServletRequestPartExceptionHandler(MissingServletRequestPartException ex) {
        Map<String, String> errorMessage = new HashMap<>();
        errorMessage.put("Error", ex.getMessage());
        return errorMessage;
    }

    @ExceptionHandler(MultipartException.class)
    public ResponseEntity<String> handleMultipartException(MultipartException ex) {
        // Customize your error message or response as needed
        String errorMessage = ex.getMessage();

        // You can log the exception here if needed
        // logger.error(errorMessage, ex);

        // You can customize the HTTP status code and the response body as needed
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorMessage);
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MaxUploadSizeExceededException.class)
    public Map<String, String> FileSizeLimitExceededExceptionHandler(MaxUploadSizeExceededException ex) {
        Map<String, String> errorMessage = new HashMap<>();
        String ErrorMessage = ex.getCause().getMessage();
        errorMessage.put("Error", ErrorMessage.substring(ErrorMessage.indexOf("The field"), ErrorMessage.length()));
        return errorMessage;
    }


}
