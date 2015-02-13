package com.danco.training.dobrilko.task4.comparator;

import java.util.Comparator;

import com.danco.training.dobrilko.task4.model.Order;

public class OrderExecutedComparator implements Comparator<Order> {
    public int compare(Order a, Order b) {
	
		return (Boolean.compare(a.getStatus(), b.getStatus()));
    }
}
