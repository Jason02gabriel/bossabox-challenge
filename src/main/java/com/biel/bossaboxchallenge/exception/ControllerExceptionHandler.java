package com.biel.bossaboxchallenge.exception;


import com.biel.bossaboxchallenge.domain.dtos.ExceptionDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerExceptionHandler {

    private static final String FOUND_MESSAGE = "Nenhum registro encontrado";
    private static final String ID_FOUND_MESSAGE = "Id não encontrado";

    @ExceptionHandler(NoRecordsFoundException.class)
    public ResponseEntity handleNoRecordsFoundException(NoRecordsFoundException e) {
        ExceptionDTO exceptionDTO = new ExceptionDTO(FOUND_MESSAGE, HttpStatus.BAD_REQUEST.toString());
        return ResponseEntity.badRequest().body(exceptionDTO);
    }

    @ExceptionHandler(IdNotFoundException.class)
    public ResponseEntity handleIdNotFoundException(IdNotFoundException e) {
        ExceptionDTO exceptionDTO = new ExceptionDTO(ID_FOUND_MESSAGE, HttpStatus.BAD_REQUEST.toString());
        return ResponseEntity.badRequest().body(exceptionDTO);
    }



}

