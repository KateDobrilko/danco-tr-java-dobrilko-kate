package com.danco.training.dobrilko.DI;

import java.lang.reflect.Field;
import java.util.ArrayList;

import org.apache.log4j.Logger;

import com.danco.training.dobrilko.API.IBookshopController;
import com.danco.training.dobrilko.DI.annotation.Inject;
import com.danco.training.dobrilko.DI.enumeration.ControllerClass;
import com.danco.training.dobrilko.PropertyController.property.PropertyStorage;

public class Injector {
	private static final String CLASS_NOT_FOUND = "Class not found!";
	private static final String ERROR_DURING_INJECTION = "Error during injection!";
	private static Logger logger = Logger.getLogger(Injector.class);

	public static <T> void inject(T object) {
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
						IBookshopController ic = (IBookshopController) Class
								.forName(injectionClassName).newInstance();
						field.set(object, ic);
					} catch (IllegalArgumentException | IllegalAccessException
							| InstantiationException | ClassNotFoundException e) {

						logger.error(ERROR_DURING_INJECTION, e);
					}
				}
			} catch (ClassNotFoundException e) {
				Logger logger = Logger.getLogger(Injector.class);
				logger.error(CLASS_NOT_FOUND, e);
			}

			field.setAccessible(false);

		}

	}
}
