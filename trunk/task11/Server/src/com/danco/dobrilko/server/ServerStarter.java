package com.danco.dobrilko.server;

import java.util.Scanner;

import com.danco.dobrilko.di.Injector;

public class ServerStarter {

	private static final String PLEASE_INPUT_SERVER_PORT_NUMBER = "Please, input server port number:";

	public static void main(String[] args) {
		System.out.println(PLEASE_INPUT_SERVER_PORT_NUMBER);
		Scanner scanner = new Scanner(System.in);
		int port = scanner.nextInt();
		Server server = new Server(port);
		Injector.inject(server);
		server.start();
		scanner.close();
	}
}
