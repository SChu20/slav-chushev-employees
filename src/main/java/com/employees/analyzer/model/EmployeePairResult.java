package com.employees.analyzer.model;


/**
 * Represents the top result
 */
public record EmployeePairResult(int empId1, int empId2, int projectId, long daysWorkedTogether) {
}
