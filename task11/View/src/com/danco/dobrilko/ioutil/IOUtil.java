package com.danco.dobrilko.ioutil;

import java.util.InputMismatchException;
import java.util.Scanner;

public class IOUtil {
	@SuppressWarnings("resource")
	public static int readInt() throws InputMismatchException{
		Scanner in = new Scanner(System.in);
		return in.nextInt();
	}

	@SuppressWarnings("resource")
	public static double readDouble() throws InputMismatchException {

		Scanner in = new Scanner(System.in);
		return in.nextDouble();

	}

	@SuppressWarnings("resource")
	public static boolean readBoolean() throws InputMismatchException{
		Scanner in = new Scanner(System.in);
		return in.nextBoolean();
	}

	@SuppressWarnings("resource")
	public static String readString() {
		Scanner in = new Scanner(System.in);
		return in.nextLine();
	}

	public static void print(String s) {
		System.out.print(s);
	}

	public static void println() {
		System.out.println();
	}
}
