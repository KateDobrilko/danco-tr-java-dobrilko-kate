package com.danco.training.dobrilko.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.danco.training.dobrilko.enumeration.PropertyType;

@Target(value = ElementType.FIELD)
@Retention(value = RetentionPolicy.RUNTIME)
public @interface CSVPrimitiveProperty {
	PropertyType propertyType = PropertyType.PrimitiveProperty;
	int positionInString = 0;
	int positionInString() default 0;

}
