package com.example.demo.exceptions;


import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class OrderException extends RuntimeException{

    private final HttpStatus httpStatus;

    public OrderException(String message, HttpStatus status){
        super(message);
        this.httpStatus = status;
    }

}
