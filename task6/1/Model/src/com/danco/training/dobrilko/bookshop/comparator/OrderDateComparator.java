package com.danco.training.dobrilko.bookshop.comparator;

import java.util.Comparator;

import com.danco.training.dobrilko.bookshop.model.Order;

public class OrderDateComparator implements Comparator<Order> {
	public int compare(Order a, Order b) {
		return a.getDate().compareTo(b.getDate());
	}
}
