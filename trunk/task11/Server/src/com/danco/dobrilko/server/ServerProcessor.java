package com.danco.dobrilko.server;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import org.apache.log4j.Logger;

import com.danco.dobrilko.api.IBookshopController;
import com.danco.dobrilko.commandmessage.CommandMessage;

public class ServerProcessor {
	private static final String EMPTY = "EMPTY";
	private static final String EXIT = "Exit";
	private static final String ILLEGAL_ACCESS_EXCEPTION = "IllegalAccessException | IllegalArgumentException| InvocationTargetException has been caught!";
	Logger logger = Logger.getLogger(ServerProcessor.class);
	private IBookshopController bsController;

	public ServerProcessor(IBookshopController bsController) {
		this.bsController = bsController;
	}

	public Object executeCommandMessage(Object CommandMessage) {
		Object answer = EMPTY;

		Class<?> bsClass = bsController.getClass();
		for (Method method : bsClass.getMethods()) {
			if (CommandMessage instanceof CommandMessage) {
				if (method.getName().equals(
						((CommandMessage) CommandMessage)
								.getNameOfMethodToCall())) {
					try {
						if (((CommandMessage) CommandMessage)
								.getNameOfMethodToCall() != EXIT) {
							if (((CommandMessage) CommandMessage).getArgs().length != 0) {
								if (method.getReturnType() != void.class) {
									answer = method.invoke(bsController,
											((CommandMessage) CommandMessage)
													.getArgs());
								} else {
									method.invoke(bsController,
											((CommandMessage) CommandMessage)
													.getArgs());
								}
							} else {
								if (method.getReturnType() != void.class) {
									answer = method.invoke(bsController);
								} else {
									method.invoke(bsController);
								}
							}
						} else {
							answer = EXIT;
						}
					} catch (IllegalAccessException | IllegalArgumentException
							| InvocationTargetException e) {

						logger.error(ILLEGAL_ACCESS_EXCEPTION, e);
					}
				}
			}
		}
		return answer;
	}
}
