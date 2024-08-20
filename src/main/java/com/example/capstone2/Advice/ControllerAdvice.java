package com.example.capstone2.Advice;

import com.example.capstone2.Api.ApiException;

import jakarta.validation.ConstraintViolationException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;
import org.springframework.web.servlet.resource.NoResourceFoundException;


@org.springframework.web.bind.annotation.ControllerAdvice


public class ControllerAdvice {


    // responsible for the exception made to avoid errors from user entries, like check from
    // the venue id before add event.
    @ExceptionHandler(value = ApiException.class)
    public ResponseEntity ApiException(ApiException e){
        String message=e.getMessage();
        return ResponseEntity.status(400).body(message);
    }

    // insure the database (ticketsystem) is connected with server, when not connected this
    // exception will show and handle the error
    @ExceptionHandler(value = InvalidDataAccessApiUsageException.class)
    public ResponseEntity InvalidDataAccessResourceUsageException(InvalidDataAccessApiUsageException e){
        return ResponseEntity.status(400).body(e.getMessage());
    }

    // to validate the entries from users and insure it meets the constraints like method add review
    // in rate value it will if the entry type not the type in method argument,
    // this exception will show and handle the error
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity MethodArgumentNotValidException(MethodArgumentNotValidException e){
        return ResponseEntity.status(400).body(e.getFieldError().getDefaultMessage());
    }

    // to insure the request type meet with mapping of the method like method addEvent we use Postmapping
    // so when call it in postman and use other request type like Get this exception will show and
    // handle the error
    @ExceptionHandler(value = HttpRequestMethodNotSupportedException.class)
    public ResponseEntity HttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e) {
        return ResponseEntity.status(400).body(e.getMessage());
    }

    // to handle repetition of unique value like email in user or coordinator.
    @ExceptionHandler(value = DataIntegrityViolationException.class)
    public ResponseEntity DataIntegrityViolationException(DataIntegrityViolationException e){

        return ResponseEntity.status(400).body(e.getMessage());
    }

    // when enter a string value in int entry, this exception will show and handle the error
    // example : age : "s" ,
    @ExceptionHandler(value = HttpMessageNotReadableException.class)
    public ResponseEntity HttpMessageNotReadableException(HttpMessageNotReadableException e) {

        return ResponseEntity.status(400).body(e.getMessage());
    }


    // in argument of method which is Pathveriable when you enter wrong datatype or date
    // with different type of type of argument of method
    @ExceptionHandler(value = MethodArgumentTypeMismatchException.class)
    public ResponseEntity MethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException e) {

        return ResponseEntity.status(400).body(e.getMessage());
    }

    // to make sure the date entries meet the constraints
    @ExceptionHandler(value = ConstraintViolationException.class)
    public ResponseEntity ConstraintViolationException(ConstraintViolationException e) {
        return ResponseEntity.status(400).body(e.getMessage());
    }

    // when write path incorrectly this handler will show and handle the error
    @ExceptionHandler(value = NoResourceFoundException.class)
    public ResponseEntity NoResourceFoundException(NoResourceFoundException e) {
        return ResponseEntity.status(400).body(e.getMessage());
    }















}
