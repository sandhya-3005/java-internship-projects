import java.math.BigDecimal;
public class Employee {
    private int employeeId;
    private BigDecimal basicSalary;
    public int getEmployeeId() {
        return employeeId;
    }

    public Employee( int employeeId,String name,BigDecimal basicSalary) {
        this.employeeId=employeeId;
        this.name=name;
        this.basicSalary = basicSalary;
    }

    public void setEmployeeId(int employeeId) {
        this.employeeId = employeeId;
    }

    private String name;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getBasicSalary() {
        return basicSalary;
    }

    public void setBasicSalary(BigDecimal basicSalary) {
        this.basicSalary = basicSalary;
    }


}
