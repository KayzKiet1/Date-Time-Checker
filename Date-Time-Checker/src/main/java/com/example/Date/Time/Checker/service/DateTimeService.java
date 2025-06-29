package com.example.Date.Time.Checker.service;

import org.springframework.stereotype.Service;
import com.example.Date.Time.Checker.model.DateTimeResponse;

import java.time.DateTimeException;
import java.time.LocalDate;

@Service
public class DateTimeService {
    
    public DateTimeResponse checkDateTime(String day, String month, String year) {
        // Check day format
        int dayValue;
        try {
            dayValue = Integer.parseInt(day);
        } catch (NumberFormatException e) {
            return new DateTimeResponse(false, "Input data for Day is incorrect format!");
        }
        
        // Check month format
        int monthValue;
        try {
            monthValue = Integer.parseInt(month);
        } catch (NumberFormatException e) {
            return new DateTimeResponse(false, "Input data for Month is incorrect format!");
        }
        
        // Check year format
        int yearValue;
        try {
            yearValue = Integer.parseInt(year);
        } catch (NumberFormatException e) {
            return new DateTimeResponse(false, "Input data for Year is incorrect format!");
        }
        
        // Check day range (1-31)
        if (dayValue < 1 || dayValue > 31) {
            return new DateTimeResponse(false, "Input data for Day is out of range!");
        }
        
        // Check month range (1-12)
        if (monthValue < 1 || monthValue > 12) {
            return new DateTimeResponse(false, "Input data for Month is out of range!");
        }
        
        // Check year range (1000-3000)
        if (yearValue < 1000 || yearValue > 3000) {
            return new DateTimeResponse(false, "Input data for Year is out of range!");
        }
        
        // Check if date is valid
        try {
            LocalDate date = LocalDate.of(yearValue, monthValue, dayValue);
            return new DateTimeResponse(true, day + "/" + month + "/" + year + " is correct date time!");
        } catch (DateTimeException e) {
            return new DateTimeResponse(false, day + "/" + month + "/" + year + " is NOT correct date time!");
        }
    }
}
