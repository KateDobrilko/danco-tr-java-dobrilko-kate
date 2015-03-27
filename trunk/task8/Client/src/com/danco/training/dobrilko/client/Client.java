package com.danco.training.dobrilko.client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import com.danco.training.dobrilko.menu.MenuController;

public class Client {

	private String serverIP;
	private int serverPort;
	private MenuController menuController;
	private ObjectInputStream in;
	private ObjectOutputStream out;

	public Client(String serverIP, int serverPort) {
		this.serverIP = serverIP;
		this.serverPort = serverPort;

	}

	public void start() {
		Socket fromServer = null;

		try {
			fromServer = new Socket(serverIP, serverPort);
			out = new ObjectOutputStream(fromServer.getOutputStream());
			in = new ObjectInputStream(fromServer.getInputStream());

			menuController = new MenuController(out, in);

			while (menuController.getExitFlag() != true) {
				menuController.run();

			}

			out.close();
			in.close();
			fromServer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
