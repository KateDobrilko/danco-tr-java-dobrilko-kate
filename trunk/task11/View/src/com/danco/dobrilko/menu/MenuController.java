package com.danco.dobrilko.menu;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import com.danco.dobrilko.ioutil.IOUtil;

public class MenuController {

	private Navigator navigator;
	private boolean exitFlag = false;

	public MenuController() {
		navigator = new Navigator();
		MenuCreator.createMenu();
		navigator.setCurrentMenu(MenuCreator.getFirstMenu());
	}

	public void run(ObjectOutputStream out, ObjectInputStream in) {

		if (navigator.getCurrentMenu() != null) {
			navigator.print();
		}
		this.exitFlag = !navigator.navigate(IOUtil.readInt(), in, out);

	}

	public boolean getExitFlag() {
		return exitFlag;
	}

}
