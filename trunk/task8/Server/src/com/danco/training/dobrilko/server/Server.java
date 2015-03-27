package com.danco.training.dobrilko.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import com.danco.training.dobrilko.controller.api.IBookshopController;
import com.danco.training.dobrilko.di.annotation.Inject;
import com.danco.training.dobrilko.di.enumeration.ControllerClass;

public class Server {
	private int port;
	@Inject(controllerClass = ControllerClass.BOOKSHOP_CONTROLLER)
	private IBookshopController bsController;

	public Server(int port) {
		this.port = port;

	}

	public void start() {

		ServerSocket serverSocket = null;
		try {
			serverSocket = new ServerSocket(port);

			while (true) {
				Socket clientSocket = serverSocket.accept();
				if (clientSocket.isConnected()) {
					System.out.println("Client connected...");
				}

				new ReadWriteThread(clientSocket, bsController)
						.start();

			}

		} catch (IOException e) {
			System.out.println("Can't accept");
			System.exit(-1);
		} finally {
			try {
				serverSocket.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

}
