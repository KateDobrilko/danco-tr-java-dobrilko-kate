package com.danco.training.dobrilko.Client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import org.apache.log4j.Logger;

import com.danco.training.dobrilko.View.menu.MenuController;

public class Client {

	private static final String IO_EXCEPTION_HAS_BEEN_CAUGHT = "IOException has been caught!";
	private String serverIP;
	private int serverPort;
	private MenuController menuController;
	private Logger logger = Logger.getLogger(Client.class);

	public Client(String serverIP, int serverPort) {
		this.serverIP = serverIP;
		this.serverPort = serverPort;

	}

	public void start() {

		try (Socket fromServer = new Socket(serverIP, serverPort);
				ObjectOutputStream out = new ObjectOutputStream(
						fromServer.getOutputStream());
				ObjectInputStream in = new ObjectInputStream(
						fromServer.getInputStream()))

		{
			menuController = new MenuController();

			while (menuController.getExitFlag() != true) {
				menuController.run(out, in);
			}
			

		} catch (IOException e) {
			
			logger.error(IO_EXCEPTION_HAS_BEEN_CAUGHT, e);
		}
	}
}
