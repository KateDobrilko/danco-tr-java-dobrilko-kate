package com.danco.training.dobrilko.di;

import java.lang.reflect.Field;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.danco.training.dobrilko.controller.api.IBookshopController;
import com.danco.training.dobrilko.di.annotation.Inject;
import com.danco.training.dobrilko.di.enumeration.ControllerClass;
import com.danco.training.dobrilko.di.property.PropertyStorage;

public class Injector {
	public static <T> void inject(T object)  {
		final Field[] declaredFields = object.getClass().getDeclaredFields();
		ArrayList<Field> fieldsToInject = new ArrayList<Field>();
		for (Field field : declaredFields) {
			if (field.isAnnotationPresent(Inject.class)) {
				fieldsToInject.add(field);
			}
		}
		for (Field field : fieldsToInject) {
			String injectionClassName = null;
			Inject inject = field.getAnnotation(Inject.class);
			if (inject.controllerClass().equals(
					ControllerClass.BOOKSHOP_CONTROLLER)) {
				injectionClassName = PropertyStorage.getInstance()
						.getBookshopControllerName();

			}
			field.setAccessible(true);
			try {
				if (field.getType().isAssignableFrom(
						Class.forName(injectionClassName))) {
					try {
						IBookshopController ic = (IBookshopController) Class.forName(injectionClassName).newInstance();
						field.set(object,
								ic);
					} catch (IllegalArgumentException | IllegalAccessException
							| InstantiationException | ClassNotFoundException e) {
						Logger logger = Logger.getLogger(Injector.class);
						logger.error("Error during injection!", e);
					}
				}
			} catch (ClassNotFoundException e) {
				Logger logger = Logger.getLogger(Injector.class);
				logger.error("Class not found!", e);
			}

			field.setAccessible(false);

		}

	}
}
