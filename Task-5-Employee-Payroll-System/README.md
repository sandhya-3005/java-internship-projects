# Employee Payroll System

## Description

A Java-based application that manages employee details and processes payroll using a PostgreSQL database.

## Features

* Register employee details.
* Store employee information in a PostgreSQL database.
* Calculate salary allowances and tax deductions.
* Calculate gross salary and net salary.
* Save payroll records in the database.
* Generate and display salary slips.

## Technologies Used

* Java
* JDK 17
* Maven
* JDBC
* PostgreSQL
* SQL

## Database

Database name: `employee_payroll_db`

The application uses PostgreSQL tables to store employee information and payroll records.

## How to Run

1. Ensure JDK 17, Maven, and PostgreSQL are installed.
2. Create the `employee_payroll_db` database and the required tables.
3. Configure the database connection details in `DatabaseConnection.java`.
4. Open the project in IntelliJ IDEA.
5. Allow Maven to download the required dependencies.
6. Run `EmployeePayrollSystem.java`.
7. Follow the menu options displayed in the console.

## Note

The salary allowance and tax percentages used in this project are illustrative values for demonstration purposes.
