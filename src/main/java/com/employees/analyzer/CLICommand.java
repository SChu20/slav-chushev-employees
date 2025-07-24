package com.employees.analyzer;

import com.employees.analyzer.model.EmployeePairResult;
import com.employees.analyzer.service.EmployeesService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.File;
import java.nio.file.Files;
import java.util.List;

@Component
public class CLICommand implements CommandLineRunner
{
    private final EmployeesService service;

    public CLICommand(EmployeesService service)
    {
        this.service = service;
    }

    @Override
    public void run(String... args) throws Exception
    {
        if (args.length < 1)
        {
            System.out.println("Usage: java -jar employee-pair-app.jar <path-to-csv>");
            return;
        }

        File f = new File(args[0]);
        if (!f.exists() || !f.isFile())
        {
            System.err.println("File not found: " + args[0]);
            System.exit(1);
        }

        List<EmployeePairResult> results = service.findTopPair(Files.newInputStream(f.toPath()));

        if (results.isEmpty())
        {
            System.out.println("No overlap in projects between employees found.");
        }
        else
        {
            results.forEach(r -> System.out.printf("%d %d %d%n",
                                                                r.empId1(),
                                                                r.empId2(),
                                                                r.daysWorkedTogether()));
        }
        System.exit(0);
    }
}