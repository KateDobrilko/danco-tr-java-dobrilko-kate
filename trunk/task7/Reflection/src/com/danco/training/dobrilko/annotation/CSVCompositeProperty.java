package com.danco.training.dobrilko.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.danco.training.dobrilko.enumeration.PropertyType;

public @interface CSVCompositeProperty {

	@Target(value = ElementType.FIELD)
	@Retention(value = RetentionPolicy.RUNTIME)
	public @interface CSVProperty {
		PropertyType propertyType = PropertyType.CompositeProperty;
		int keyField = 0;
	}
}
