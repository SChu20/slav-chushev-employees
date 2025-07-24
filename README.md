# Employee Analyzer 

Identifies the pair of employees who have worked together on common projects for the longest time (in total days), based on a CSV input file.

It supports both:
Web-based interface (via Thymeleaf) on port :8080
CLI mode

Used Techologies:

Java 17+
Spring Boot 3
Thymeleaf
JUnit 5

Features

Upload a CSV file containing employee project history.
Find the pair with the most total days worked together, even across multiple projects.
Show a detailed project-by-project breakdown.
CLI support.

CSV Format:

Input CSV should have the following structure:

EmpID, ProjectID, DateFrom, DateTo
143, 12, 2013-11-01, 2014-01-05
218, 10, 2012-05-16, NULL
143, 10, 2009-01-01, 2011-04-27

Usage:

Build the project - mvn clean install

CLI mode run java -jar target/analyzer-0.0.1-SNAPSHOT.jar path/to/employees.csv

UI mode run java -jar target/analyzer-0.0.1-SNAPSHOT.jar
Go to: http://localhost:8080
