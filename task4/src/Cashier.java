public class Cashier {
    private double commonSum;

    public Cashier(double commonSum) {
	this.commonSum = commonSum;
    }

    public Cashier() {
    }

    public void addSum(double sum) {
	commonSum += sum;
    }

    public double getCommonSum() {
	return commonSum;
    }

    @Override
    public String toString() {
	return Double.toString(commonSum);
    }

}
