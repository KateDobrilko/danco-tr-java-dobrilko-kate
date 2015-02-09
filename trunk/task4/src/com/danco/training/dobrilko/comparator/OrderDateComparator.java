package com.danco.training.dobrilko.comparator;

import java.util.Comparator;

import com.danco.training.dobrilko.model.Order;

public class OrderDateComparator implements Comparator<Order> {
    public int compare(Order a, Order b) {
	return a.getDate().compareTo(b.getDate());
    }
}
