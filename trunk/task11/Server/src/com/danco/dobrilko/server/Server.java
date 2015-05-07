package com.danco.dobrilko.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import org.apache.log4j.Logger;

import com.danco.dobrilko.api.IBookshopController;
import com.danco.dobrilko.di.annotation.Inject;
import com.danco.dobrilko.di.enumeration.ControllerClass;

public class Server {
	private static final String IO_EXCEPTION_HAS_BEEN_CAUGHT = "IOException has been caught!";
	private static final String CAN_T_ACCEPT = "Can't accept";
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
			System.out.println(CAN_T_ACCEPT);
			System.exit(-1);
		} finally {
			try {
				serverSocket.close();
			} catch (IOException e) {
				
				logger.error(IO_EXCEPTION_HAS_BEEN_CAUGHT, e);
			}
		}

	}

}
