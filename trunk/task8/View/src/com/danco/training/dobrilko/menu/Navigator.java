package com.danco.training.dobrilko.menu;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import com.danco.training.dobrilko.command.Command;
import com.danco.training.dobrilko.ioutil.IOUtil;

public class Navigator {

	private ObjectInputStream in;
	private ObjectOutputStream out;

	public Navigator(ObjectInputStream in, ObjectOutputStream out) {
		this.in = in;
		this.out = out;
	}

	private Menu currentMenu = new Menu();

	public Menu getCurrentMenu() {
		return currentMenu;
	}

	public void setCurrentMenu(Menu currentMenu) {
		this.currentMenu = currentMenu;
	}

	public boolean navigate(int index) {
		if (this.currentMenu != null) {
			if ((index < currentMenu.getMenuItems().size()) && (index >= 0)) {
				try {
					out.writeObject((this.currentMenu.getItem(index)
							.sendCommand()));
					this.currentMenu.getItem(index).receiveAnswer(
							in.readObject());
				} catch (IOException | ClassNotFoundException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				setCurrentMenu(this.currentMenu.getItem(index).getMenu());
			} else {
				setCurrentMenu(null);
			}
			return true;
		}

		else {

			try {
				out.writeObject(new Command("Exit", null));
				this.currentMenu.getItem(index).receiveAnswer(in.readObject());
			} catch (IOException | ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			return false;
		}
	}

	public void print() {
		StringBuilder sb = new StringBuilder();
		for (MenuItem mi : currentMenu.getMenuItems()) {
			sb.append(mi.getTitle() + System.lineSeparator());
		}

		IOUtil.print(sb.toString());
	}

}
