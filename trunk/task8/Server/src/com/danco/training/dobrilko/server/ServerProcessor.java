package com.danco.training.dobrilko.server;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.apache.log4j.Logger;

import com.danco.training.dobrilko.command.Command;
import com.danco.training.dobrilko.controller.api.IBookshopController;

public class ServerProcessor {

	private IBookshopController bsController;

	public ServerProcessor(IBookshopController bsController) {
		this.bsController = bsController;
	}

	public Object executeCommand(Object command) {
		Object answer = null;

		Class<?> bsClass = bsController.getClass();
		for (Method method : bsClass.getMethods()) {
			if (command instanceof Command) {
				if (method.getName().equals(
						((Command) command).getNameOfMethodToCall())) {
					try {
						if (((Command) command).getNameOfMethodToCall() != "Exit") {
							if (((Command) command).getArgs().length != 0) {
								if (method.getReturnType() != void.class) {
									answer = method.invoke(bsController,
											((Command) command).getArgs());
								} else {
									method.invoke(bsController,
											((Command) command).getArgs());
								}
							} else {
								if (method.getReturnType() != void.class) {
									answer = method.invoke(bsController);
								} else {
									method.invoke(bsController);
								}
							}
						} else {

						}
					} catch (IllegalAccessException | IllegalArgumentException
							| InvocationTargetException e) {
						Logger logger = Logger.getLogger(ServerProcessor.class);
						logger.error(
								"IllegalAccessException | IllegalArgumentException| InvocationTargetException has been caught!",
								e);
					}
				}
			}
		}
		return answer;
	}
}
