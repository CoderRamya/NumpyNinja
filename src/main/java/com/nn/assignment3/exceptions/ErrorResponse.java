package com.nn.assignment3.exceptions;

public class ErrorResponse {
    private String message;
    private String details;

    // Constructor, getters, setters
    public ErrorResponse(String message, String details) {
        this.message = message;
        this.details = details;
    }

    // Getters and setters
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }
}
