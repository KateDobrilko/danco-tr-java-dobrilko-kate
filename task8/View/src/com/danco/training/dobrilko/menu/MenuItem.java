package com.danco.training.dobrilko.menu;

import com.danco.training.dobrilko.action.IAction;
import com.danco.training.dobrilko.command.Command;

public class MenuItem {
	private IAction action;
	private String title;
	private Menu menu;

	public MenuItem(IAction action, String title, Menu menu) {
		this.action = action;
		this.title = title;
		this.menu = menu;

	}

	public MenuItem(String title, Menu menu) {
		this.action = null;
		this.title = title;
		this.menu = menu;

	}

	public Command sendCommand() {

		return action.sendCommand();

	}

	public void receiveAnswer(Object obj) {

		action.receiveAnswer(obj);

	}

	public Menu getMenu() {
		return menu;
	}

	public String getTitle() {
		return title;
	}

}
