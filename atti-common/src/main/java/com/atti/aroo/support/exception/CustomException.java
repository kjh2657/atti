package com.atti.aroo.support.exception;

import com.atti.aroo.support.dto.Error;

public class CustomException extends RuntimeException{

    private static final long serialVersionUID = 1L;

    private String message;

    public CustomException(){

    }
    public CustomException(String message){
        this.message = message;
    }



    @Override
    public String getMessage() {
        return message;
    }
}
