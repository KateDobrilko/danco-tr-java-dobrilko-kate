package com.danco.dobrilko.di.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.danco.dobrilko.di.enumeration.ControllerClass;

@Target(value = ElementType.FIELD)
@Retention(value = RetentionPolicy.RUNTIME)
public @interface Inject {

	ControllerClass controllerClass() default ControllerClass.BOOKSHOP_CONTROLLER;
}
