package com.danco.training.dobrilko.comparator;

import java.util.Comparator;

import com.danco.training.dobrilko.entity.Order;

public class OrderPriceComparator implements Comparator<Order> {
	public int compare(Order a, Order b) {

		return (Double.compare(a.getSum(), b.getSum()));
	}
}
