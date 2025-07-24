package com.employees.analyzer.util;

import com.employees.analyzer.model.EmployeeEntry;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CSVParser {

    public static List<EmployeeEntry> parseCSV(InputStream is) throws IOException
    {
        try (BufferedReader br = new BufferedReader(new InputStreamReader(is)))
        {
            List<EmployeeEntry> result = new ArrayList<>();
            String line;
            while ((line = br.readLine()) != null) {
                if (line.isBlank() || line.startsWith("EmpID")) continue;
                String[] parts = line.split(",\\s*"); // 4 parts: EmpID, ProjectID, DateFrom, DateTo
                LocalDate from = RobustDateFormatter.parseDate(parts[2]);
                LocalDate to = parts[3].equalsIgnoreCase("NULL") ? LocalDate.now()
                                                                            : RobustDateFormatter.parseDate(parts[3]);
                result.add(new EmployeeEntry(Integer.parseInt(parts[0]), Integer.parseInt(parts[1]), from, to));
            }
            return result;
        }
        catch (IOException e)
        {
            throw new IOException("Error during CSV reading" + e);
        }
    }
}
