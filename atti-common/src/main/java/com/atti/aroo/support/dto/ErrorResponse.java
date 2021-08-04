package com.atti.aroo.support.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
public class ErrorResponse {

    String statusCode;
    String requestUrl;
    String message;
    String resultCode;

    List<Error> errors = new ArrayList<>();

    public ErrorResponse() {
        this.resultCode = "FAIL";
    }

    public Error addError(Error error){
        this.errors.add(error);
        return error;
    }

}
