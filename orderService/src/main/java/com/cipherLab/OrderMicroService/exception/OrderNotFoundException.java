package com.cipherLab.OrderMicroService.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class OrderNotFoundException extends RuntimeException {
    public OrderNotFoundException(Long id) {
        super("Could not find book " + id);
    }

    public OrderNotFoundException(String cell) {
        super("Could not find book associated with cellNumber : " + cell);
    }
}
