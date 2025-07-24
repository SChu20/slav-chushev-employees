package com.employees.analyzer.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Locale;

public class RobustDateFormatter {
    //reasonable date formats day-first sorted to handle ambiguous dates ex: 03/01/25 => 3rd of January 2025
    //Could use external lib Natty to handle fallback cases
    private static final List<DateTimeFormatter> DATE_FORMATS = List.of(
            DateTimeFormatter.ofPattern("dd/MM/yyyy"),
            DateTimeFormatter.ofPattern("dd-MM-yyyy"),
            DateTimeFormatter.ofPattern("dd.MM.yyyy"),
            DateTimeFormatter.ofPattern("dd MMM yyyy", Locale.ENGLISH),
            DateTimeFormatter.ofPattern("ddMMyyyy"),
            DateTimeFormatter.ofPattern("MM/dd/yyyy"),
            DateTimeFormatter.ofPattern("MM-dd-yyyy"),
            DateTimeFormatter.ofPattern("MM.dd.yyyy"),
            DateTimeFormatter.ofPattern("MMM dd, yyyy", Locale.ENGLISH),
            DateTimeFormatter.ofPattern("yyyy-MM-dd"),
            DateTimeFormatter.ofPattern("yyyy/MM/dd"),
            DateTimeFormatter.ofPattern("yyyy.MM.dd"),
            DateTimeFormatter.ofPattern("yyyyMMdd")
    );

    public static LocalDate parseDate(String date)
    {
        String trimmedDate = date.trim();
        for (DateTimeFormatter formatter : DATE_FORMATS)
        {
            try
            {
                return LocalDate.parse(trimmedDate, formatter);
            }
            catch (DateTimeParseException ignored) {}
        }
        throw new IllegalArgumentException("Unsupported or invalid date: " + date);
    }

}
