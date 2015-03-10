package com.danco.training.dobrilko.menu;

import com.danco.training.dobrilko.action.IAction;

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

	public void doAction() {
		if (action != null) {
			action.execute();
		} else {

		}
	}

	public Menu getMenu() {
		return menu;
	}

	public String getTitle() {
		return title;
	}

}

