package com.example.Date.Time.Checker.model;

public class DateTimeResponse {
    private boolean success;
    private String message;
    
    public DateTimeResponse() {
    }
    
    public DateTimeResponse(boolean success, String message) {
        this.success = success;
        this.message = message;
    }
    
    public boolean isSuccess() {
        return success;
    }
    
    public void setSuccess(boolean success) {
        this.success = success;
    }
    
    public String getMessage() {
        return message;
    }
    
    public void setMessage(String message) {
        this.message = message;
    }
}
