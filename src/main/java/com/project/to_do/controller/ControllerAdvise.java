package com.project.to_do.controller;

import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
public class ControllerAdvise {

    @ExceptionHandler(BindException.class)
    public ResponseEntity<HashMap<String, List<String>>> handleExceptions(BindException err){
        List<String> exceptions = err.getAllErrors()
                .stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.toList());

        HashMap<String, List<String>> errorMap = new HashMap<>();
        errorMap.put("error",exceptions);

        return ResponseEntity
                .badRequest()
                .body(errorMap);
    }
}
