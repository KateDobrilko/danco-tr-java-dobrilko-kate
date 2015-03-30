package com.danco.training.dobrilko.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import org.apache.log4j.Logger;

import com.danco.training.dobrilko.controller.api.IBookshopController;
import com.danco.training.dobrilko.di.annotation.Inject;
import com.danco.training.dobrilko.di.enumeration.ControllerClass;

public class Server {
	private int port;
	@Inject(controllerClass = ControllerClass.BOOKSHOP_CONTROLLER)
	private IBookshopController bsController;
	private Logger logger = Logger.getLogger(Server.class);
	public Server(int port) {
		this.port = port;

	}

	public void start() {

		ServerSocket serverSocket = null;
		try {
			serverSocket = new ServerSocket(port);

			while (true) {
				Socket clientSocket = serverSocket.accept();

				new ReadWriteThread(clientSocket, bsController).start();

			}

		} catch (IOException e) {
			System.out.println("Can't accept");
			System.exit(-1);
		} finally {
			try {
				serverSocket.close();
			} catch (IOException e) {
				
				logger.error("IOException has been caught!", e);
			}
		}

	}

}
