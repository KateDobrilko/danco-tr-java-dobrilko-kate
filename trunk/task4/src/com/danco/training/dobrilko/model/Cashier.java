package com.danco.training.dobrilko.model;
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
	StringBuilder sb = new StringBuilder();
	sb.append("Cashier "+System.lineSeparator());
	sb.append( Double.toString(commonSum));
	return sb.toString();
    }

}
