package com.spring.wev.config;

import com.spring.wev.dto.ExceptionDto;
import jakarta.transaction.SystemException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class CustomExceptionHandler
{
    @ExceptionHandler(SystemException.class)
    ResponseEntity<ExceptionDto>handleSystemException(SystemException exception)
    {
        return ResponseEntity.internalServerError().body(new ExceptionDto("",exception.getMessage()));
    }
    @ExceptionHandler(RuntimeException.class)
    ResponseEntity<ExceptionDto>handleRunTimeException(RuntimeException exception)
    {
        return ResponseEntity.internalServerError().body(new ExceptionDto("",exception.getMessage()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String,String>> handleValidationError(MethodArgumentNotValidException ex)
    {
        Map<String,String>errors=new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error->{
            errors.put(error.getField(),error.getDefaultMessage());
        });
        return  new ResponseEntity<>(errors,HttpStatus.BAD_REQUEST);
    }

}



