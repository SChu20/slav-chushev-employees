package com.employees.analyzer.util;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

public class RobustDateFormatterTest {

    @Test
    void testParseValidDateFormatsDayFirst() {
        assertEquals(LocalDate.of(2023, 12, 25), RobustDateFormatter.parseDate("25/12/2023"));
        assertEquals(LocalDate.of(2022, 1, 31), RobustDateFormatter.parseDate("31-01-2022"));
        assertEquals(LocalDate.of(2020, 3, 15), RobustDateFormatter.parseDate("15.03.2020"));
        assertEquals(LocalDate.of(2023, 2, 1), RobustDateFormatter.parseDate("01 Feb 2023"));
        assertEquals(LocalDate.of(2024, 2, 12), RobustDateFormatter.parseDate("12022024"));
    }

    @Test
    void testParseValidDateFormatsEdgeCases() {
        assertEquals(LocalDate.of(2024, 2, 12), RobustDateFormatter.parseDate("2024-02-12"));
        assertEquals(LocalDate.of(2023, 12, 31), RobustDateFormatter.parseDate("12/31/2023"));
        assertEquals(LocalDate.of(2023, 12, 25), RobustDateFormatter.parseDate("Dec 25, 2023"));
        assertEquals(LocalDate.of(2024, 2, 12), RobustDateFormatter.parseDate("20240212"));
    }

    @Test
    void testParseInvalidDate() {
        Exception ex = assertThrows(IllegalArgumentException.class, () -> RobustDateFormatter.parseDate("invalid"));
        assertTrue(ex.getMessage().contains("Unsupported"));
    }
}
