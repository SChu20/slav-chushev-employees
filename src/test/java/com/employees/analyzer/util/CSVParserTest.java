package com.employees.analyzer.util;

import com.employees.analyzer.model.EmployeeEntry;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class CSVParserTest {

    @Test
    void testParseCSV() throws Exception {
        String csv = "EmpID, ProjectID, DateFrom, DateTo\n" +
                "143, 10, 01/01/2025, 01/06/2025\n" +
                "218, 10, 01/03/2025, NULL\n";

        List<EmployeeEntry> projects = CSVParser.parseCSV(new ByteArrayInputStream(csv.getBytes()));
        assertEquals(2, projects.size());

        EmployeeEntry e1 = projects.get(0);
        assertEquals(143, e1.id());
        assertEquals(10, e1.projectId());
        assertEquals(LocalDate.of(2025, 1, 1), e1.dateFrom());
        assertEquals(LocalDate.of(2025, 6, 1), e1.dateTo());

        EmployeeEntry e2 = projects.get(1);
        assertEquals(218, e2.id());
        assertEquals(10, e2.projectId());
        assertTrue(e2.dateTo().isAfter(LocalDate.of(2025, 1, 1))); // since NULL = today
    }
}
