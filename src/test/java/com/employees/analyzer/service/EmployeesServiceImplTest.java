package com.employees.analyzer.service;

import com.employees.analyzer.model.EmployeePairResult;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class EmployeesServiceImplTest {


    private EmployeesServiceImpl service;

    @BeforeEach
    void setUp() {
        service = new EmployeesServiceImpl();
    }

    @Test
    void testFindTopPair() throws Exception {
        String csv = "EmpID, ProjectID, DateFrom, DateTo\n" +
                "100, 1, 01/01/2020, 01/03/2020\n" +
                "101, 1, 15/01/2020, 01/03/2020\n" + // 46 days
                "100, 2, 01/04/2020, 01/05/2020\n" +
                "101, 2, 15/04/2020, 01/05/2020\n";

        List<EmployeePairResult> results = service.findTopPair(new ByteArrayInputStream(csv.getBytes()));

        assertEquals(2, results.size());
        assertEquals(100, results.getFirst().empId1());
        assertEquals(101, results.getFirst().empId2());
        assertEquals(1, results.getFirst().projectId());
        assertTrue(results.getFirst().daysWorkedTogether() >= 45);
    }
}