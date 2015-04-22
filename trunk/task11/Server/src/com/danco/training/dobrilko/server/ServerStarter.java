package com.danco.training.dobrilko.server;

import java.util.Scanner;

import com.danco.training.dobrilko.di.Injector;

public class ServerStarter {

	public static void main(String[] args) {
		System.out.println("Please, input server port number:");
		Scanner scanner = new Scanner(System.in);
		int port = scanner.nextInt();
		Server server = new Server(port);
		Injector.inject(server);
		server.start();
		scanner.close();
	}
}
