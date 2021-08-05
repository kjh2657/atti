package com.atti.aroo.support.advice;


import com.atti.aroo.support.dto.Error;
import com.atti.aroo.support.dto.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintViolationException;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@RestControllerAdvice(annotations = RestController.class)
public class ApiControllerAdvice {

    @ExceptionHandler(ArithmeticException.class)
    public ResponseEntity arithmeticException(ArithmeticException e, HttpServletRequest httpServletRequest) {

        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setMessage(e.getMessage());
        errorResponse.setStatusCode(BAD_REQUEST.toString());
        errorResponse.setRequestUrl(httpServletRequest.getRequestURI());

        return ResponseEntity.status(BAD_REQUEST).body(errorResponse);
    }

    @ExceptionHandler(NullPointerException.class)
    public ResponseEntity nullPointerException(NullPointerException e, HttpServletRequest httpServletRequest) {

        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setMessage(e.getMessage());
        errorResponse.setStatusCode(INTERNAL_SERVER_ERROR.toString());
        errorResponse.setRequestUrl(httpServletRequest.getRequestURI());

        return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(errorResponse);
    }


    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity missingServletRequestParameterException(MissingServletRequestParameterException e, HttpServletRequest httpServletRequest) {

        Error error = new Error();
        error.setField(e.getParameterName());
        error.setMessage(e.getMessage());

        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setMessage(e.getMessage());
        errorResponse.setStatusCode(BAD_REQUEST.toString());
        errorResponse.setRequestUrl(httpServletRequest.getRequestURI());
        errorResponse.addError(error);

        return ResponseEntity.status(BAD_REQUEST).body(errorResponse);
    }



    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity constraintViolationException(ConstraintViolationException e, HttpServletRequest httpServletRequest) {

        List<Error> errors = new ArrayList<>();

        e.getConstraintViolations().forEach(err -> {

            Error error = new Error();
            error.setField(err.getPropertyPath().toString());

            error.setInvalidValue(err.getInvalidValue() == null ? "null" : err.getInvalidValue().toString());
            error.setMessage(err.getMessage());

            errors.add(error);
        });

        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.setErrors(errors);
        errorResponse.setMessage("");
        errorResponse.setRequestUrl(httpServletRequest.getRequestURI());
        errorResponse.setStatusCode(BAD_REQUEST.toString());

        return ResponseEntity.status(BAD_REQUEST).body(errorResponse);
    }

    //CustomException구현 후 여기서 받기

}
