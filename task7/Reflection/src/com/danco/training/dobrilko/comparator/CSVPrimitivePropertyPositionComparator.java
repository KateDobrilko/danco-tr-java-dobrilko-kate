package com.danco.training.dobrilko.comparator;

import java.lang.reflect.Field;
import java.util.Comparator;

import com.danco.training.dobrilko.annotation.CSVPrimitiveProperty;

public class CSVPrimitivePropertyPositionComparator implements
		Comparator<Field> {

	public int compare(Field a, Field b) {
		if ((a.isAnnotationPresent(CSVPrimitiveProperty.class))
				&& (b.isAnnotationPresent(CSVPrimitiveProperty.class))) {
			return ((Integer) a.getAnnotation(CSVPrimitiveProperty.class)
					.positionInString()).compareTo(((Integer) (Integer) b
					.getAnnotation(CSVPrimitiveProperty.class)
					.positionInString()));
		} else {
			return 0;
		}
	}

}
