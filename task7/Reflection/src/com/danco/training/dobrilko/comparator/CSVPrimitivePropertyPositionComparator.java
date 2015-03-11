package com.danco.training.dobrilko.comparator;

import java.util.Comparator;

import com.danco.training.dobrilko.annotation.CSVPrimitiveProperty;

public class CSVPrimitivePropertyPositionComparator implements
		Comparator<CSVPrimitiveProperty> {

	public int compare(CSVPrimitiveProperty a, CSVPrimitiveProperty b) {

		return ((Integer) a.positionInString()).compareTo(((Integer) b
				.positionInString()));
	}

}
