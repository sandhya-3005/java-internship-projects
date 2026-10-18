import java.math.BigDecimal;

public class TestPayroll {

    public static void main(String[] args) {

        PayrollService service = new PayrollService();

        BigDecimal basicSalary = new BigDecimal("30000");
        BigDecimal allowances =
                service.calculateAllowances(basicSalary);

        BigDecimal grossSalary =
                service.calculateGrossSalary(
                        basicSalary, allowances);

        BigDecimal tax =
                service.calculateTax(grossSalary);

        BigDecimal otherDeductions =
                new BigDecimal("500");

        BigDecimal netSalary =
                service.calculateNetSalary(
                        grossSalary, tax, otherDeductions);

        System.out.println("Basic Salary: " + basicSalary);
        System.out.println("Allowances: " + allowances);
        System.out.println("Gross Salary: " + grossSalary);
        System.out.println("Tax: " + tax);
        System.out.println("Other Deductions: " + otherDeductions);
        System.out.println("Net Salary: " + netSalary);
    }
}