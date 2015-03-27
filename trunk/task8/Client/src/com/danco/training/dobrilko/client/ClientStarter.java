package com.danco.training.dobrilko.client;

import java.util.Scanner;

public class ClientStarter {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		System.out.println("Please enter server ip:");
		String ip = scanner.nextLine();
		System.out.println("Please enter server port:");
		int port = scanner.nextInt();
		Client client = new Client(ip, port);
		client.start();
		scanner.close();
	}

}
