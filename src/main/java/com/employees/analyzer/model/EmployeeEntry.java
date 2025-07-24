package com.employees.analyzer.model;


import java.time.LocalDate;


/**
 * Represents one entry of the CSV
 */
public record EmployeeEntry(int id, int projectId, LocalDate dateFrom, LocalDate dateTo) {
}