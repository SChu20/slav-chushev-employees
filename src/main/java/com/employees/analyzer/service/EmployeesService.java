package com.employees.analyzer.service;


import com.employees.analyzer.model.EmployeePairResult;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

public interface EmployeesService {
    List<EmployeePairResult> findTopPair(InputStream is) throws IOException;
}
