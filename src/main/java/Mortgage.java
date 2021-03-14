
public class Mortgage {
    private String customer;
    private float totalLoan;
    private float interest;
    private int years;
    private double monthlyPayment;

    public Mortgage(String customer, float totalLoan, float interest, int years) {
        this.customer = customer;
        this.totalLoan = totalLoan;
        this.interest = interest;
        this.years = years;
    }

    public void calculateMonthlyPayment() {
        double b = (interest / 100) / 12;    // interest rate
        double U = totalLoan;                // total loan
        int p = years * 12;                 // number of payments
        double E = U * ((b * pow(1+b, p)) / (pow(1+b, p) -1));

        this.monthlyPayment = roundTo2Decimals(E);
    }

    public double pow(double base, int power) {
        double num = 1;
        for(int i = 0; i < power; i++) {
            num = num * base;
        }
        return num;
    }

    public double roundTo2Decimals(double num) {
        num *= 100.0;
        num += 0.5;
        num = (int)num;
        num /= 100.0;

        return num;
    }

    public String getCustomer() {
        return customer;
    }
    public void setCustomer(String c) {
        this.customer = c;
    }

    public float getTotalLoan() {
        return totalLoan;
    }
    public void setTotalLoan(float t) {
        this.totalLoan = t;
    }

    public float getInterest() {
        return interest;
    }
    public void setInterest(float i) {
        this.interest = i;
    }

    public int getYears() {
        return years;
    }
    public void setYears(int y) {
        this.years = y;
    }

    public double getMonthlyPayment() {
        return monthlyPayment;
    }
}
