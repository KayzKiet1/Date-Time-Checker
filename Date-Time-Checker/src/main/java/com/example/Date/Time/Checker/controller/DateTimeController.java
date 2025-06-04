package com.example.Date.Time.Checker.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.Date.Time.Checker.model.DateTimeRequest;
import com.example.Date.Time.Checker.model.DateTimeResponse;
import com.example.Date.Time.Checker.service.DateTimeService;

@RestController
public class DateTimeController {
    
    @Autowired
    private DateTimeService dateTimeService;
    
    @PostMapping("/api/check-date")
    public ResponseEntity<DateTimeResponse> checkDate(@RequestBody DateTimeRequest request) {
        DateTimeResponse response = dateTimeService.checkDateTime(
            request.getDay(), 
            request.getMonth(), 
            request.getYear()
        );
        
        return ResponseEntity.ok(response);
    }
}
