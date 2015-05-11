package com.danco.training.dobrilko.Client;

import java.util.Scanner;

public class ClientStarter {

	private static final String PLEASE_ENTER_SERVER_PORT = "Please enter server port:";
	private static final String PLEASE_ENTER_SERVER_IP = "Please enter server ip:";

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		System.out.println(PLEASE_ENTER_SERVER_IP);
		String ip = scanner.nextLine();
		System.out.println(PLEASE_ENTER_SERVER_PORT);
		int port = scanner.nextInt();
		Client client = new Client(ip, port);
		client.start();
		scanner.close();
	}

}
