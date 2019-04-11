package com.labs.maven.springBoot.SpringBootMSC.ExceptionHandler;

import com.labs.maven.springBoot.SpringBootMSC.Model.ExceptionMessage;
import com.labs.maven.springBoot.SpringBootMSC.ServerExceptions.InvalidInfoException;
import com.labs.maven.springBoot.SpringBootMSC.ServerExceptions.ThereIsNoSuchItemException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ExceptionHandler400_404 extends ResponseEntityExceptionHandler {

    @ExceptionHandler(InvalidInfoException.class)
    protected ResponseEntity<ExceptionMessage> handleInvalidInfoException() {
        return new ResponseEntity<>(new ExceptionMessage("Invalid typed value", "400"), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ThereIsNoSuchItemException.class)
    protected ResponseEntity<ExceptionMessage> handleThereIsNoSuchItemException() {
        return new ResponseEntity<>(new ExceptionMessage("There is no such user", "404"), HttpStatus.NOT_FOUND);
    }
}
