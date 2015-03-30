package com.danco.training.dobrilko.menu;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import org.apache.log4j.Logger;

import com.danco.training.dobrilko.command.Command;
import com.danco.training.dobrilko.ioutil.IOUtil;

public class Navigator {

	private Logger logger = Logger.getLogger(Logger.class);
	public Navigator() {

	}

	private Menu currentMenu = new Menu();

	public Menu getCurrentMenu() {
		return currentMenu;
	}

	public void setCurrentMenu(Menu currentMenu) {
		this.currentMenu = currentMenu;
	}

	public boolean navigate(int index, ObjectInputStream in,
			ObjectOutputStream out) {
		if (this.currentMenu != null) {
			if ((index < currentMenu.getMenuItems().size()) && (index >= 0)) {
				try {
					if (this.currentMenu.getItem(index).getAction() != null) {

						out.writeObject((this.currentMenu.getItem(index)
								.sendCommand()));
						out.flush();

						if (in.readObject() != null) {
							this.currentMenu.getItem(index).receiveAnswer(
									in.readObject());
						}

					}
					if ((this.currentMenu.getItem(index).getAction() != null)
							&& (this.currentMenu.getItem(index).getMenu() == null)) {
						out.writeObject(new Command("Exit", null));
					}

				} catch (IOException | ClassNotFoundException e) {
					
					logger.error("Exception found!", e);

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
				
				logger.error("Exception found!", e);
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
