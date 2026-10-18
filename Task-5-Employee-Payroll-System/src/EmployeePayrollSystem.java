
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class EmployeePayrollSystem {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n===== EMPLOYEE PAYROLL SYSTEM =====");
            System.out.println("1. Register Employee");
            System.out.println("2. Process Payroll and Generate Salary Slip");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");

            int choice;

            try {
                choice = scanner.nextInt();
            } catch (java.util.InputMismatchException e) {
                System.out.println("Please enter a valid number.");
                scanner.nextLine();
                continue;
            }

            switch (choice) {
                case 1:
                    registerEmployee(scanner);
                    break;

                case 2:
                    processPayroll(scanner);
                    break;

                case 3:
                    System.out.println("Exiting Payroll System. Goodbye!");
                    scanner.close();
                    return;

                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }

    private static void registerEmployee(Scanner scanner) {

        try {
            System.out.print("Enter Employee ID: ");
            int id = scanner.nextInt();
            scanner.nextLine();

            System.out.print("Enter Employee Name: ");
            String name = scanner.nextLine().trim();

            if (name.isEmpty()) {
                System.out.println("Employee name cannot be empty.");
                return;
            }

            System.out.print("Enter Basic Salary: ");
            BigDecimal salary = scanner.nextBigDecimal();

            if (salary.compareTo(BigDecimal.ZERO) < 0) {
                System.out.println("Salary cannot be negative.");
                return;
            }

            Employee employee = new Employee(id, name, salary);

            String sql = "INSERT INTO employees "
                    + "(employee_id, name, basic_salary) "
                    + "VALUES (?, ?, ?)";

            try (Connection connection = DatabaseConnection.getConnection();
                 PreparedStatement statement =
                         connection.prepareStatement(sql)) {

                statement.setInt(1, employee.getEmployeeId());
                statement.setString(2, employee.getName());
                statement.setBigDecimal(3, employee.getBasicSalary());

                statement.executeUpdate();
                System.out.println("Employee saved successfully!");
            }

        } catch (java.util.InputMismatchException e) {
            System.out.println("Invalid input. Please enter the correct data type.");
            scanner.nextLine();
        } catch (SQLException e) {
            System.out.println("Error saving employee: " + e.getMessage());
        }
    }

    private static void processPayroll(Scanner scanner) {

        try {
            System.out.print("Enter Employee ID: ");
            int employeeId = scanner.nextInt();

            scanner.nextLine();

            System.out.print("Enter payroll month (YYYY-MM-01): ");
            String monthInput = scanner.nextLine().trim();

            java.sql.Date payMonth =
                    java.sql.Date.valueOf(monthInput);

            System.out.print("Enter other deductions: ");
            BigDecimal otherDeductions = scanner.nextBigDecimal();

            if (otherDeductions.compareTo(BigDecimal.ZERO) < 0) {
                System.out.println("Deductions cannot be negative.");
                return;
            }

            String findSql =
                    "SELECT name, basic_salary FROM employees "
                            + "WHERE employee_id = ?";

            try (Connection connection = DatabaseConnection.getConnection();
                 PreparedStatement findStatement =
                         connection.prepareStatement(findSql)) {

                findStatement.setInt(1, employeeId);

                try (ResultSet result = findStatement.executeQuery()) {

                    if (!result.next()) {
                        System.out.println("Employee not found!");
                        return;
                    }

                    String name = result.getString("name");
                    BigDecimal basicSalary =
                            result.getBigDecimal("basic_salary");

                    PayrollService service = new PayrollService();

                    BigDecimal allowances =
                            service.calculateAllowances(basicSalary);

                    BigDecimal grossSalary =
                            service.calculateGrossSalary(
                                    basicSalary, allowances);

                    BigDecimal tax =
                            service.calculateTax(grossSalary);

                    BigDecimal netSalary =
                            service.calculateNetSalary(
                                    grossSalary, tax, otherDeductions);

                    if (netSalary.compareTo(BigDecimal.ZERO) < 0) {
                        System.out.println(
                                "Payroll failed: deductions exceed the remaining salary.");
                        return;
                    }

                    String insertSql =
                            "INSERT INTO payroll "
                                    + "(employee_id, basic_salary, allowances, "
                                    + "gross_salary, tax, other_deductions, "
                                    + "net_salary, pay_month) "
                                    + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

                    try (PreparedStatement insertStatement =
                                 connection.prepareStatement(insertSql)) {

                        insertStatement.setInt(1, employeeId);
                        insertStatement.setBigDecimal(2, basicSalary);
                        insertStatement.setBigDecimal(3, allowances);
                        insertStatement.setBigDecimal(4, grossSalary);
                        insertStatement.setBigDecimal(5, tax);
                        insertStatement.setBigDecimal(6, otherDeductions);
                        insertStatement.setBigDecimal(7, netSalary);
                        insertStatement.setDate(8, payMonth);

                        insertStatement.executeUpdate();

                        System.out.println("\n========== SALARY SLIP ==========");
                        System.out.println("Employee ID      : " + employeeId);
                        System.out.println("Employee Name    : " + name);
                        System.out.println("Pay Month        : " + payMonth);
                        System.out.println("---------------------------------");
                        System.out.println("Basic Salary     : " + basicSalary);
                        System.out.println("Allowances        : " + allowances);
                        System.out.println("Gross Salary     : " + grossSalary);
                        System.out.println("Tax              : " + tax);
                        System.out.println("Other Deductions : " + otherDeductions);
                        System.out.println("---------------------------------");
                        System.out.println("Net Salary       : " + netSalary);
                        System.out.println("=================================");
                        System.out.println("Payroll saved successfully!");
                    }
                }
            }

        } catch (java.util.InputMismatchException e) {
            System.out.println("Invalid input. Please enter the correct data type.");
            scanner.nextLine();
        } catch (SQLException | IllegalArgumentException e) {
            System.out.println("Payroll processing failed: " + e.getMessage());
        }
    }
}