package com.danco.training.dobrilko.file;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class FileWorker {

	public String readFromFile(String path) {
		StringBuilder sb = new StringBuilder();
		try (BufferedReader br = new BufferedReader(new FileReader(path))) {

			String line;
			while ((line = br.readLine()) != null) {

				sb.append(line);
				sb.append(System.lineSeparator());

			}

		} catch (IOException e) {

			System.out.println("IOException has been found in FileWorker!");
		}

		return sb.toString();

	}

	public void writeInFile(String path, String output) {
		StringBuilder sb = new StringBuilder();
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(path,
				false));) {
			sb.append(output);
			writer.write(sb.toString());
		} catch (IOException e) {
			System.out.println("IOException has been found in FileWorker!");
		}
	}
}
