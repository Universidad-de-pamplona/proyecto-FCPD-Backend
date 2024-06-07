package com.touchIon.backend.exception;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class ErrorResponse {
    private String message;
    private int status;
    private String details;

    public ErrorResponse(String message, int status, String details) {
        this.message = message;
        this.status = status;
        this.details = details;
    }

    // Getters y setters

}
