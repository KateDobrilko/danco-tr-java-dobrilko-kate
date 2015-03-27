package com.danco.training.dobrilko.server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

import com.danco.training.dobrilko.controller.api.IBookshopController;

public class ReadWriteThread extends Thread {

	private Socket fromClient;
	@SuppressWarnings("unused")
	private IBookshopController bsController;
	private ObjectInputStream in = null;
	private ObjectOutputStream out = null;
	private Object input;
	private Object output;
	private ServerProcessor serverProcessor;

	public ReadWriteThread(Socket fromClient, IBookshopController bsController) {
		this.fromClient = fromClient;
		this.setBsController(bsController);
		this.serverProcessor = new ServerProcessor(bsController);

	}

	@Override
	public void run() {
		try {
			output = new ObjectOutputStream(fromClient.getOutputStream());
			input = new ObjectInputStream(fromClient.getInputStream());

			while ((input = in.readObject()) != null) {

				output = serverProcessor.executeCommand(input);
				if (output == null) {
					break;
				}
				if (output != null) {
					
					out.writeObject(output);
					out.flush();

				}

			}
		} catch (IOException e) {
			e.printStackTrace();
			// TODO
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			try {
				fromClient.close();

				out.close();

				in.close();
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
