package com.danco.training.dobrilko.server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import org.apache.log4j.Logger;

import com.danco.training.dobrilko.controller.api.IBookshopController;

public class ReadWriteThread extends Thread {

	private Socket fromClient;
	@SuppressWarnings("unused")
	private IBookshopController bsController;

	private ServerProcessor serverProcessor;

	public ReadWriteThread(Socket fromClient, IBookshopController bsController) {
		this.fromClient = fromClient;
		this.setBsController(bsController);
		this.serverProcessor = new ServerProcessor(bsController);

	}

	@Override
	public void run() {
		try {
			ObjectOutputStream oos = new ObjectOutputStream(
					fromClient.getOutputStream());
			ObjectInputStream ois = new ObjectInputStream(
					fromClient.getInputStream());
			Object input;
			Object output;
			while ((input = ois.readObject()) != null) {

				output = serverProcessor.executeCommand(input);
				if (output == null) {
					break;
				}
				if (output != null) {
					oos.reset();
					oos.writeObject(output);
					oos.flush();
				}

			}
			oos.close();
			ois.close();
			fromClient.close();
		} catch (IOException e) {
			Logger logger = Logger.getLogger(ReadWriteThread.class);
			logger.error("IOException has been caught!", e);
		} catch (ClassNotFoundException e) {
			Logger logger = Logger.getLogger(ReadWriteThread.class);
			logger.error("ClassNotFoundException has been caught!", e);
		}
	}

	public void setBsController(IBookshopController bsController) {
		this.bsController = bsController;
	}

}
