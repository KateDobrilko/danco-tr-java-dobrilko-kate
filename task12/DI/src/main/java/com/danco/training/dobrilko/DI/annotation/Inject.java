package com.danco.training.dobrilko.DI.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import com.danco.training.dobrilko.DI.enumeration.ControllerClass;

@Target(value = ElementType.FIELD)
@Retention(value = RetentionPolicy.RUNTIME)
public @interface Inject {

	ControllerClass controllerClass() default ControllerClass.BOOKSHOP_CONTROLLER;
}
