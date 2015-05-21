package com.danco.training.dobrilko.csvworker;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import com.danco.training.dobrilko.entity.Employee;

public class CSVWorker {

	public static String readCSVFile(String path) {

		StringBuilder sb = new StringBuilder();
		try (BufferedReader br = new BufferedReader(new FileReader(path))) {

			String line;
			while ((line = br.readLine()) != null) {

				sb.append(line);
				sb.append(System.lineSeparator());

			}

		} catch (IOException e) {

			System.out.println("IOException has been found in CSVWorker!");
		}

		return sb.toString();

	}

	@SuppressWarnings("deprecation")
	public static List<Employee> parseCSVString(String csvString) {
		List<Employee> employees = new LinkedList<Employee>();
		String[] strings = csvString.split(System.lineSeparator());
		for (String string : strings) {
			String[] substrings = string.split(";");

			String firstName = substrings[0];
			String lastName = substrings[1];
			String[] dateString = substrings[2].split("-");
			Date birthDate = new Date(Integer.parseInt(dateString[0]),
					Integer.parseInt(dateString[1]),
					Integer.parseInt(dateString[2]));
			Boolean gender = substrings[3].equals("male");
			Integer id = Integer.parseInt(substrings[4]);
			String title = substrings[5];

			employees.add(new Employee(id, firstName, lastName, gender,
					birthDate, title));

		}

		return employees;

	}
}
