package com.danco.training.dobrilko.comparator;

import java.util.Comparator;

import com.danco.training.dobrilko.entitiy.Order;

public class OrderExecutedComparator implements Comparator<Order> {
	public int compare(Order a, Order b) {

		return (Boolean.compare(a.getStatus(), b.getStatus()));
	}
}
