package com.danco.training.dobrilko.ioutil;

import java.util.Scanner;

public class IOUtil {

	@SuppressWarnings("resource")
	public static int readInt() {
		Scanner in = new Scanner(System.in);
		return in.nextInt();
	}

	@SuppressWarnings("resource")
	public static double readDouble() {
		Scanner in = new Scanner(System.in);
		return in.nextDouble();
	}

	@SuppressWarnings("resource")
	public static String readString() {
		Scanner in = new Scanner(System.in);
		return in.nextLine();
	}

	public static void print(String s) {
		System.out.println(s);
	}
}
