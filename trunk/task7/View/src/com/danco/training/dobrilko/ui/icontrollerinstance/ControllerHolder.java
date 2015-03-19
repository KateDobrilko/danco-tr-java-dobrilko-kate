package com.danco.training.dobrilko.ui.icontrollerinstance;

import com.danco.training.dobrilko.controller.api.IController;
import com.danco.training.dobrilko.di.Injector;
import com.danco.training.dobrilko.di.annotation.Inject;
import com.danco.training.dobrilko.di.enumeration.ControllerClass;

public class ControllerHolder {

	private static ControllerHolder instance;

	@Inject(controllerClass = ControllerClass.BOOKSHOP_CONTROLLER)
	private IController controller;

	private ControllerHolder() {
		
	}

	public static ControllerHolder getInstance() {
		if (instance == null) {
			instance = new ControllerHolder();
		}
		Injector.inject(instance);
		return instance;

	}

	public IController getController() {
		return this.controller;
	}
}
