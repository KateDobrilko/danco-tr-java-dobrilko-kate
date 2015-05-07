package com.danco.dobrilko.menu;

import com.danco.dobrilko.action.IAction;
import com.danco.dobrilko.commandmessage.CommandMessage;

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

	public CommandMessage sendCommandMessage() {

		return action.sendCommandMessage();

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

	public IAction getAction() {
		return action;

	}

}
