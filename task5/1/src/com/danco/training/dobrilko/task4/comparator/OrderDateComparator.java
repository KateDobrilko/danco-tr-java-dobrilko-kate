package com.danco.training.dobrilko.task4.comparator;

import java.util.Comparator;

import com.danco.training.dobrilko.task4.model.Order;

public class OrderDateComparator implements Comparator<Order> {
    public int compare(Order a, Order b) {
	return a.getDate().compareTo(b.getDate());
    }
}
