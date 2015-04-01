package com.danco.training.dobrilko.number;

import java.util.ArrayList;

public class Summarizer {

	public static Integer getSum(ArrayList<Integer> array) {
		Integer sum = 0;
		for (Integer item : array) {
			sum += item;
		}
		return sum;

	}

}
