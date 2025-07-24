package com.employees.analyzer.service;

import com.employees.analyzer.model.EmployeeEntry;
import com.employees.analyzer.model.EmployeePairResult;
import com.employees.analyzer.util.CSVParser;
import org.springframework.stereotype.Service;

import java.io.*;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class EmployeesServiceImpl implements EmployeesService  {


    @Override
    public List<EmployeePairResult> findTopPair(InputStream is) throws IOException {
        Map<Integer, List<EmployeeEntry>> byProject = CSVParser.parseCSV(is).stream()
                .collect(Collectors.groupingBy(EmployeeEntry::projectId));

        Map<String, List<EmployeePairResult>> pairDetails = new HashMap<>();

        for (List<EmployeeEntry> group : byProject.values()) {
            for (int i = 0; i < group.size(); i++) {
                for (int j = i + 1; j < group.size(); j++) {
                    EmployeeEntry a = group.get(i);
                    EmployeeEntry b = group.get(j);
                    if (a.id() == b.id()) continue; // exclude self
                    long overlap = getOverlapDays(a.dateFrom(), a.dateTo(), b.dateFrom(), b.dateTo());

                    if (overlap > 0) {
                        String key = generatePairKey(a.id(), b.id());
                        pairDetails.computeIfAbsent(key, k -> new ArrayList<>())
                                .add(new EmployeePairResult(a.id(), b.id(), a.projectId(), overlap));
                    }
                }
            }
        }

        // Find the pair with the highest total overlap
        return pairDetails.entrySet().stream()
                .max(Comparator.comparingLong(e -> e.getValue().stream().mapToLong(EmployeePairResult::daysWorkedTogether).sum()))
                .map(Map.Entry::getValue)
                .orElse(List.of());
    }


    public long getOverlapDays(LocalDate from1, LocalDate to1, LocalDate from2, LocalDate to2) {
        LocalDate start = from1.isAfter(from2) ? from1 : from2;
        LocalDate end = to1.isBefore(to2) ? to1 : to2;
        return !start.isAfter(end) ? ChronoUnit.DAYS.between(start, end) : 0;
    }

    public String generatePairKey(int emp1, int emp2) {
        return emp1 < emp2 ? emp1 + "-" + emp2 : emp2 + "-" + emp1;
    }

}
