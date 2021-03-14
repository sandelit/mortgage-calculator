import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MortgageTest {
    Mortgage mortgage = new Mortgage("John Doe", 10000, 3, 5);

    @Test
    void testPaymentCalculator() {
        float totalLoan = mortgage.getTotalLoan();
        float interest = mortgage.getInterest();
        int years = mortgage.getYears();

        mortgage.calculateMonthlyPayment();

        Assertions.assertEquals(179.69, mortgage.getMonthlyPayment());
    }

    @Test
    void testPow() {
        double base = 4;
        double numSquared = mortgage.pow(base, 2);
        double numCubed = mortgage.pow(base, 3);

        Assertions.assertEquals(16, numSquared);
        Assertions.assertEquals(64, numCubed);
    }

    @Test
    void testRounding() {
        double roundedDecimal = mortgage.roundTo2Decimals(2.532);
        Assertions.assertEquals(2.53, roundedDecimal);
    }
}