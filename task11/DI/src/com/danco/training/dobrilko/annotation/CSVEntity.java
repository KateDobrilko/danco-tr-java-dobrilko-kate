package com.danco.training.dobrilko.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import com.danco.training.dobrilko.enumeration.CSVFileReflectionPath;

@Target(value = ElementType.TYPE)
@Retention(value = RetentionPolicy.RUNTIME)
public @interface CSVEntity {	
	CSVFileReflectionPath csvPath() default CSVFileReflectionPath.BOOK_REFLECTION_PATH;
	char valuesSeparator () default ';';
	
	
}
