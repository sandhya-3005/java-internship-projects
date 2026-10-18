
import java.math.BigDecimal;
import java.math.RoundingMode;

public class PayrollService {

    private static final BigDecimal ALLOWANCE_RATE =
            new BigDecimal("0.10");

    private static final BigDecimal TAX_RATE =
            new BigDecimal("0.10");

    public BigDecimal calculateAllowances(BigDecimal basicSalary) {
        return basicSalary.multiply(ALLOWANCE_RATE)
                .setScale(2, RoundingMode.HALF_UP);
    }

    public BigDecimal calculateGrossSalary(
            BigDecimal basicSalary, BigDecimal allowances) {
        return basicSalary.add(allowances)
                .setScale(2, RoundingMode.HALF_UP);
    }

    public BigDecimal calculateTax(BigDecimal grossSalary) {
        return grossSalary.multiply(TAX_RATE)
                .setScale(2, RoundingMode.HALF_UP);
    }

    public BigDecimal calculateNetSalary(
            BigDecimal grossSalary,
            BigDecimal tax,
            BigDecimal otherDeductions) {

        return grossSalary.subtract(tax)
                .subtract(otherDeductions)
                .setScale(2, RoundingMode.HALF_UP);
    }
}