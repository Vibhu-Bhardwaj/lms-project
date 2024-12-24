package org.example.exception;

import org.example.dtos.ResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;

@ControllerAdvice
public class ExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ResponseDto<Object>> handleExceptions(RuntimeException e){
        ResponseDto responseDto = new ResponseDto();
        responseDto.setStatus("error");
        responseDto.setErrorMessage(e.getMessage());
        ResponseEntity responseEntity = new ResponseEntity(responseDto, HttpStatus.INTERNAL_SERVER_ERROR);
        return responseEntity;

    }
}
