package com.danco.training.dobrilko.server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import org.apache.log4j.Logger;

import com.danco.training.dobrilko.controller.api.IBookshopController;

public class ReadWriteThread extends Thread {
	private Logger logger = Logger.getLogger(ReadWriteThread.class);
	private Socket fromClient;
	@SuppressWarnings("unused")
	private IBookshopController bsController;

	private ServerProcessor serverProcessor;
	private ObjectOutputStream oos;
	private ObjectInputStream ois;

	public ReadWriteThread(Socket fromClient, IBookshopController bsController) {
		this.fromClient = fromClient;
		this.setBsController(bsController);
		this.serverProcessor = new ServerProcessor(bsController);

	}

	@Override
	public void run() {
		try {
			oos = new ObjectOutputStream(fromClient.getOutputStream());
			ois = new ObjectInputStream(fromClient.getInputStream());
			Object input;
			Object output;
			while ((input = ois.readObject()) != null) {

				output = serverProcessor.executeCommand(input);
				if (output == "Exit") {

					break;
				}
				if (output == "EMPTY") {
					oos.reset();
					oos.writeObject(null);
					oos.flush();
				}
				if (output != null) {
					oos.reset();
					oos.writeObject(output);
					oos.flush();
				}

			}

		} catch (IOException e) {

			logger.error("IOException has been caught!", e);
		} catch (ClassNotFoundException e) {
			
			logger.error("ClassNotFoundException has been caught!", e);
		} finally {
			try {
				oos.close();
				ois.close();
				fromClient.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public void setBsController(IBookshopController bsController) {
		this.bsController = bsController;
	}

}
