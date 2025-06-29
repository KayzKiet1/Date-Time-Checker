package com.example.Date.Time.Checker.service;

import com.example.Date.Time.Checker.model.DateTimeResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("DateTimeService Test Suite - Optimized")
public class DateTimeServiceTest {

    private DateTimeService dateTimeService;

    @BeforeEach
    void setUp() {
        dateTimeService = new DateTimeService();
    }

    // =========================== FORMAT VALIDATION TESTS ===========================

    @ParameterizedTest
    @DisplayName("Test Invalid Format Input")
    @ValueSource(strings = {"a", "xyz", "1.5", ""})
    void testInvalidFormatInput(String invalidInput) {
        // Test Day invalid format
        DateTimeResponse response = dateTimeService.checkDateTime(invalidInput, "1", "2000");
        assertFalse(response.isSuccess());
        assertEquals("Input data for Day is incorrect format!", response.getMessage());

        // Test Month invalid format
        response = dateTimeService.checkDateTime("1", invalidInput, "2000");
        assertFalse(response.isSuccess());
        assertEquals("Input data for Month is incorrect format!", response.getMessage());

        // Test Year invalid format
        response = dateTimeService.checkDateTime("1", "1", invalidInput);
        assertFalse(response.isSuccess());
        assertEquals("Input data for Year is incorrect format!", response.getMessage());
    }

    // =========================== DAY RANGE TESTS ===========================

    @Test
    @DisplayName("Test Day Out of Range - Lower Bound")
    void testDayOutOfRangeLower() {
        DateTimeResponse response = dateTimeService.checkDateTime("0", "1", "2000");
        assertFalse(response.isSuccess());
        assertEquals("Input data for Day is out of range!", response.getMessage());
    }

    @Test
    @DisplayName("Test Day Out of Range - Upper Bound")
    void testDayOutOfRangeUpper() {
        DateTimeResponse response = dateTimeService.checkDateTime("32", "1", "2000");
        assertFalse(response.isSuccess());
        assertEquals("Input data for Day is out of range!", response.getMessage());
    }

    @ParameterizedTest
    @DisplayName("Test Day Valid Range")
    @ValueSource(strings = {"1", "15", "28", "30", "31"})
    void testDayValidRange(String day) {
        DateTimeResponse response = dateTimeService.checkDateTime(day, "1", "2000");
        assertTrue(response.isSuccess());
        assertTrue(response.getMessage().contains("is correct date time!"));
    }

    // =========================== MONTH RANGE TESTS ===========================

    @Test
    @DisplayName("Test Month Out of Range - Lower Bound")
    void testMonthOutOfRangeLower() {
        DateTimeResponse response = dateTimeService.checkDateTime("1", "0", "2000");
        assertFalse(response.isSuccess());
        assertEquals("Input data for Month is out of range!", response.getMessage());
    }

    @Test
    @DisplayName("Test Month Out of Range - Upper Bound")
    void testMonthOutOfRangeUpper() {
        DateTimeResponse response = dateTimeService.checkDateTime("1", "13", "2000");
        assertFalse(response.isSuccess());
        assertEquals("Input data for Month is out of range!", response.getMessage());
    }

    @ParameterizedTest
    @DisplayName("Test Month Valid Range")
    @ValueSource(strings = {"1", "2", "4", "6", "12"})
    void testMonthValidRange(String month) {
        DateTimeResponse response = dateTimeService.checkDateTime("1", month, "2000");
        assertTrue(response.isSuccess());
        assertTrue(response.getMessage().contains("is correct date time!"));
    }

    // =========================== YEAR RANGE TESTS ===========================

    @Test
    @DisplayName("Test Year Out of Range - Lower Bound")
    void testYearOutOfRangeLower() {
        DateTimeResponse response = dateTimeService.checkDateTime("1", "1", "999");
        assertFalse(response.isSuccess());
        assertEquals("Input data for Year is out of range!", response.getMessage());
    }

    @Test
    @DisplayName("Test Year Out of Range - Upper Bound")
    void testYearOutOfRangeUpper() {
        DateTimeResponse response = dateTimeService.checkDateTime("1", "1", "3001");
        assertFalse(response.isSuccess());
        assertEquals("Input data for Year is out of range!", response.getMessage());
    }

    @ParameterizedTest
    @DisplayName("Test Year Valid Range")
    @ValueSource(strings = {"1000", "1200", "1305", "2000", "3000"})
    void testYearValidRange(String year) {
        DateTimeResponse response = dateTimeService.checkDateTime("1", "1", year);
        assertTrue(response.isSuccess());
        assertTrue(response.getMessage().contains("is correct date time!"));
    }

    // =========================== DATE VALIDITY EDGE CASES ===========================

    @Test
    @DisplayName("Test Leap Year February 29th - Valid")
    void testLeapYearFebruaryValid() {
        DateTimeResponse response = dateTimeService.checkDateTime("29", "2", "2000");
        assertTrue(response.isSuccess());
        assertEquals("29/2/2000 is correct date time!", response.getMessage());
    }

    @Test
    @DisplayName("Test Non-Leap Year February 29th - Invalid")
    void testNonLeapYearFebruaryInvalid() {
        DateTimeResponse response = dateTimeService.checkDateTime("29", "2", "1999");
        assertFalse(response.isSuccess());
        assertEquals("29/2/1999 is NOT correct date time!", response.getMessage());
    }

    @Test
    @DisplayName("Test April 31st - Invalid Date")
    void testAprilThirtyFirstInvalid() {
        DateTimeResponse response = dateTimeService.checkDateTime("31", "4", "2000");
        assertFalse(response.isSuccess());
        assertEquals("31/4/2000 is NOT correct date time!", response.getMessage());
    }

    @Test
    @DisplayName("Test February 30th - Invalid Date")
    void testFebruaryThirtiethInvalid() {
        DateTimeResponse response = dateTimeService.checkDateTime("30", "2", "2000");
        assertFalse(response.isSuccess());
        assertEquals("30/2/2000 is NOT correct date time!", response.getMessage());
    }

    // =========================== SPECIFIC INPUT DATA TESTS ===========================
    // Tests based on provided input data: Day: 1,31,28,29,30,32,0,a
    // Month: 1,12,2,4,13,0,a Year: 1000,3000,1200,1305,999,3001,a

    @Test
    @DisplayName("Test All Provided Input Combinations")
    void testSpecificInputCombinations() {
        // Test boundary valid dates
        assertTrue(dateTimeService.checkDateTime("1", "1", "1000").isSuccess());
        assertTrue(dateTimeService.checkDateTime("31", "12", "3000").isSuccess());
        assertTrue(dateTimeService.checkDateTime("28", "2", "2000").isSuccess());
        assertTrue(dateTimeService.checkDateTime("30", "4", "2000").isSuccess());

        // Test boundary invalid inputs
        assertFalse(dateTimeService.checkDateTime("32", "1", "2000").isSuccess());
        assertFalse(dateTimeService.checkDateTime("1", "13", "2000").isSuccess());
        assertFalse(dateTimeService.checkDateTime("1", "1", "999").isSuccess());
        assertFalse(dateTimeService.checkDateTime("1", "1", "3001").isSuccess());
    }
}
