package com.example.java.spring.eureka.demo.ExceptionHandler;

import com.example.java.spring.eureka.demo.Model.ExceptionMessage;
import com.example.java.spring.eureka.demo.ServerException.InvalidInfoException;
import com.example.java.spring.eureka.demo.ServerException.ThereIsNoSuchItemException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;

public class Handler400_404 {

    @ExceptionHandler(InvalidInfoException.class)
    protected ResponseEntity<ExceptionMessage> handleInvalidInfoException() {
        return new ResponseEntity<>(new ExceptionMessage("Invalid typed value", "400"), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ThereIsNoSuchItemException.class)
    protected ResponseEntity<ExceptionMessage> handleThereIsNoSuchItemException() {
        return new ResponseEntity<>(new ExceptionMessage("There is no such user", "404"), HttpStatus.NOT_FOUND);
    }
}
