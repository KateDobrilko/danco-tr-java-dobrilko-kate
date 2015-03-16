package com.danco.training.dobrilko.processor;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

import org.apache.log4j.Logger;

import com.danco.training.dobrilko.annotation.CSVCompositeList;
import com.danco.training.dobrilko.annotation.CSVCompositeProperty;
import com.danco.training.dobrilko.annotation.CSVEntity;
import com.danco.training.dobrilko.annotation.CSVPrimitiveProperty;
import com.danco.training.dobrilko.comparator.CSVPrimitivePropertyPositionComparator;
import com.danco.training.dobrilko.enumeration.CSVFileReflectionPath;
import com.danco.training.dobrilko.interfaceholder.HasId;
import com.danco.training.dobrilko.reflectionproperty.PropertyStorage;

public class AnnotationProcessor {
	private static AnnotationProcessor instance;

	private AnnotationProcessor() {

	}

	public static AnnotationProcessor getInstance() {
		if (instance == null) {
			instance = new AnnotationProcessor();
		}

		return instance;

	}

	@SuppressWarnings({ "deprecation", "unchecked" })
	public void writeInFile(Object[] objects) throws IllegalArgumentException,
			IOException, IllegalAccessException, NoSuchFieldException,
			SecurityException {

		CSVEntity csvEntity = objects[0].getClass().getAnnotation(
				CSVEntity.class);
		String fileName = "";
		if (csvEntity.csvPath() == CSVFileReflectionPath.BOOK_REFLECTION_PATH) {
			fileName = PropertyStorage.getInstance()
					.getCSVBookReflectionFilePath();
		}
		if (csvEntity.csvPath() == CSVFileReflectionPath.ORDER_REFLECTION_PATH) {
			fileName = PropertyStorage.getInstance()
					.getCSVOrderReflectionFilePath();
		}
		if (csvEntity.csvPath() == CSVFileReflectionPath.REPLY_REFLECTION_PATH) {
			fileName = PropertyStorage.getInstance()
					.getCSVReplyReflectionFilePath();
		}
		FileWriter writer = new FileWriter(fileName, false);
		StringBuilder sb = new StringBuilder();
		for (Object object : objects) {

			Field[] fields = object.getClass().getDeclaredFields();
			Arrays.sort(fields, new CSVPrimitivePropertyPositionComparator());
			ArrayList<CSVPrimitiveProperty> cpp = new ArrayList<CSVPrimitiveProperty>();
			for (Field field : fields) {
				if (field.isAnnotationPresent(CSVPrimitiveProperty.class)) {
					cpp.add(field.getAnnotation(CSVPrimitiveProperty.class));
				}
			}

			for (CSVPrimitiveProperty cp : cpp) {
				for (Field field : fields) {
					if (field.isAnnotationPresent(CSVPrimitiveProperty.class)) {
						if (field.getAnnotation(CSVPrimitiveProperty.class)
								.equals(cp)) {

							Class<?> type = field.getType();
							if (type.equals(int.class)) {

								Integer value = 0;
								Field newField = object.getClass()
										.getDeclaredField(field.getName());
								newField.setAccessible(true);
								value = (Integer) newField.get(object);
								sb.append(value);
								sb.append(csvEntity.valuesSeparator());
							}
							if (type.equals(double.class)) {
								Double value = 0.0;
								Field newField = object.getClass()
										.getDeclaredField(field.getName());
								newField.setAccessible(true);
								value = (Double) newField.get(object);

								sb.append(value);
								sb.append(csvEntity.valuesSeparator());
							}

							if (type.equals(boolean.class)) {
								Boolean value = false;
								Field newField = object.getClass()
										.getDeclaredField(field.getName());
								newField.setAccessible(true);
								value = (Boolean) newField.get(object);
								sb.append(value);
								sb.append(csvEntity.valuesSeparator());
							}
							if (type.equals(String.class)) {
								String value = "";
								Field newField = object.getClass()
										.getDeclaredField(field.getName());
								newField.setAccessible(true);
								value = (String) newField.get(object);
								sb.append(value);
								sb.append(csvEntity.valuesSeparator());
							}
							if (type.equals(Date.class)) {
								Date value = new Date();

								Field newField = object.getClass()
										.getDeclaredField(field.getName());
								newField.setAccessible(true);
								value = (Date) newField.get(object);

								if (value == null) {

								} else {
									sb.append(value.getDate());
									sb.append(csvEntity.valuesSeparator());
									sb.append(value.getMonth());
									sb.append(csvEntity.valuesSeparator());
									sb.append(value.getYear());
									sb.append(csvEntity.valuesSeparator());
								}
							}

						}

					}

				}

			}
			for (Field field : fields) {
				if (field.isAnnotationPresent(CSVCompositeList.class)) {
					Class<?> type = field.getType();
					if (type.equals(ArrayList.class)) {

						ArrayList<Object> value = new ArrayList<Object>();
						Field newField = object.getClass().getDeclaredField(
								field.getName());
						newField.setAccessible(true);
						value = (ArrayList<Object>) newField.get(object);

						for (Object obj : value) {
							if (obj instanceof HasId) {
								sb.append(System.lineSeparator());
								sb.append(((HasId) obj).getId());
								sb.append(csvEntity.valuesSeparator());

							}
						}
					}

				}

			}
			for (Field field : fields) {
				if (field.isAnnotationPresent(CSVCompositeProperty.class)) {
					sb.append(System.lineSeparator());
					Object value = new Object();
					Field newField = object.getClass().getDeclaredField(
							field.getName());
					newField.setAccessible(true);
					value = newField.get(object);

					if (value instanceof HasId) {
						sb.append(((HasId) value).getId());
						sb.append(csvEntity.valuesSeparator());
					}
				}
			}
			sb.append(System.lineSeparator());
		}

		writer.append(sb.toString());
		writer.flush();
		writer.close();
	}

	@SuppressWarnings("deprecation")
	public Object[] readFromFile(Class<?> clazz)
			throws IllegalArgumentException, IOException,
			IllegalAccessException, NoSuchFieldException, SecurityException,
			NoSuchMethodException, InstantiationException,
			InvocationTargetException {
		ArrayList<Object> objects = new ArrayList<Object>();

		Logger logger = Logger.getLogger(AnnotationProcessor.class);
		BufferedReader br = null;
		String line = "";
		ArrayList<String> strings = new ArrayList<String>();
		String fileName = "";
		try {
			if (clazz.getName() == "com.danco.training.dobrilko.entity.Book") {
				fileName = PropertyStorage.getInstance()
						.getCSVBookReflectionFilePath();
			}
			if (clazz.getName() == "com.danco.training.dobrilko.entity.Order") {
				fileName = PropertyStorage.getInstance()
						.getCSVOrderReflectionFilePath();
			}
			if (clazz.getName() == "com.danco.training.dobrilko.entity.Reply") {
				fileName = PropertyStorage.getInstance()
						.getCSVReplyReflectionFilePath();
			}
			br = new BufferedReader(new FileReader(fileName));
			while ((line = br.readLine()) != null) {

				// use comma as separator
				strings.add(line);

			}

		} catch (FileNotFoundException e) {
			logger.error("File Not Found. Check your input,", e);
		} catch (IOException e) {
			logger.error("IOException catched!");
		} finally {
			if (br != null) {
				try {
					br.close();
				} catch (IOException e) {
					logger.error("IOException catched!");
				}
			}
		}

		try {

			if (clazz.getName() == "com.danco.training.dobrilko.entity.Book") {
				int currentPointerPosition = 0;

				while (currentPointerPosition < strings.size()) {
					{
						CSVPrimitiveProperty cpp;
						String[] bookDescription = strings.get(
								currentPointerPosition).split(";");
						currentPointerPosition++;
						Date dateOfAddition;
						if (bookDescription.length < 11) {
							dateOfAddition = null;
						} else {
							cpp = clazz.getDeclaredField("dateOfAddition")
									.getAnnotation(CSVPrimitiveProperty.class);

							dateOfAddition = new Date(

							Integer.parseInt(bookDescription[cpp
									.positionInString() + 2]),
									Integer.parseInt(bookDescription[cpp
											.positionInString() + 1]),
									Integer.parseInt(bookDescription[cpp
											.positionInString()]));

						}
						cpp = clazz.getDeclaredField("dateOfPublication")
								.getAnnotation(CSVPrimitiveProperty.class);
						Date dateOfPublication = new Date(
								Integer.parseInt(bookDescription[cpp
										.positionInString() + 2]),
								Integer.parseInt(bookDescription[cpp
										.positionInString() + 1]),
								Integer.parseInt(bookDescription[cpp
										.positionInString()]));
						cpp = clazz.getDeclaredField("id").getAnnotation(
								CSVPrimitiveProperty.class);
						int id = Integer.parseInt(bookDescription[cpp
								.positionInString()]);
						cpp = clazz.getDeclaredField("name").getAnnotation(
								CSVPrimitiveProperty.class);
						String name = bookDescription[cpp.positionInString()];
						cpp = clazz.getDeclaredField("author").getAnnotation(
								CSVPrimitiveProperty.class);
						String author = bookDescription[cpp.positionInString()];
						cpp = clazz.getDeclaredField("ordered").getAnnotation(
								CSVPrimitiveProperty.class);
						boolean ordered = bookDescription[cpp
								.positionInString()].equals("true");
						cpp = clazz.getDeclaredField("numberOfExemplars")
								.getAnnotation(CSVPrimitiveProperty.class);
						int numberOfExemplars = Integer
								.parseInt(bookDescription[cpp
										.positionInString()]);
						cpp = clazz.getDeclaredField("price").getAnnotation(
								CSVPrimitiveProperty.class);
						double price = Double.parseDouble(bookDescription[cpp
								.positionInString()]);
						Class<?>[] parameters = { String.class, String.class,
								double.class, int.class, Date.class,
								Date.class, boolean.class, int.class };
						Constructor<?> constr = clazz
								.getConstructor(parameters);

						objects.add(constr.newInstance(name, author, price, id,
								dateOfAddition, dateOfPublication, ordered,
								numberOfExemplars));

					}
				}

			}

			if (clazz.getName() == "com.danco.training.dobrilko.entity.Order") {
				int currentPointerPosition = 0;

				while (currentPointerPosition < strings.size()) {

					CSVPrimitiveProperty cpp;
					String[] infoLine = strings.get(currentPointerPosition)
							.split(";");
					currentPointerPosition++;
					cpp = clazz.getDeclaredField("id").getAnnotation(
							CSVPrimitiveProperty.class);
					int id = Integer.parseInt(infoLine[cpp.positionInString()]);
					cpp = clazz.getDeclaredField("numberOfBooks")
							.getAnnotation(CSVPrimitiveProperty.class);
					int numberOfBooks = Integer.parseInt(infoLine[cpp
							.positionInString()]);
					cpp = clazz.getDeclaredField("dateOfExecution")
							.getAnnotation(CSVPrimitiveProperty.class);
					Date dateOfExecution = new Date();
					if (infoLine.length < 3) {
						dateOfExecution = null;
					} else {
						dateOfExecution.setYear(Integer.parseInt(infoLine[cpp
								.positionInString() + 2]));
						dateOfExecution.setMonth(Integer.parseInt(infoLine[cpp
								.positionInString() + 1]));
						dateOfExecution.setDate(Integer.parseInt(infoLine[cpp
								.positionInString()]));

					}

					if (dateOfExecution == null) {
						ArrayList<Integer> bookIds = new ArrayList<Integer>();
						for (int j = 0; j < numberOfBooks; j++) {
							String[] bookDescription = strings.get(
									currentPointerPosition).split(";");
							currentPointerPosition++;
							Integer bookId = Integer
									.parseInt(bookDescription[0]);
							bookIds.add(bookId);

						}
						Class<?>[] parameters = { ArrayList.class, int.class,
								boolean.class, Date.class };
						Constructor<?> constr = clazz
								.getConstructor(parameters);
						Object object = constr.newInstance(bookIds, id, true,
								null);
						objects.add(object);

					} else {
						ArrayList<Integer> bookIds = new ArrayList<Integer>();

						for (int j = 0; j < numberOfBooks; j++) {
							String[] bookDescription = strings.get(
									currentPointerPosition).split(";");
							currentPointerPosition++;
							Integer bookId = Integer
									.parseInt(bookDescription[0]);
							bookIds.add(bookId);

						}
						Class<?>[] parameters = { ArrayList.class, int.class,
								boolean.class, Date.class };
						Constructor<?> constr = clazz
								.getConstructor(parameters);
						Object object = constr.newInstance(bookIds, id, false,
								dateOfExecution);
						objects.add(object);

					}
				}

			}

			if (clazz.getName() == "com.danco.training.dobrilko.entity.Reply") {
				int currentPointerPosition = 0;

				while (currentPointerPosition < strings.size()) {

					CSVPrimitiveProperty cpp;
					String[] infoLine = strings.get(currentPointerPosition)
							.split(";");
					currentPointerPosition++;
					cpp = clazz.getDeclaredField("id").getAnnotation(
							CSVPrimitiveProperty.class);
					int id = Integer.parseInt(infoLine[cpp.positionInString()]);
					cpp = clazz.getDeclaredField("executed").getAnnotation(
							CSVPrimitiveProperty.class);
					boolean executed = infoLine[cpp.positionInString()]
							.equals("true");
					cpp = clazz.getDeclaredField("numberOfRequests")
							.getAnnotation(CSVPrimitiveProperty.class);
					int numberOfReplies = Integer.parseInt(infoLine[cpp
							.positionInString()]);

					String[] bookDescription = strings.get(
							currentPointerPosition).split(";");
					currentPointerPosition++;
					int bookId = Integer.parseInt(bookDescription[0]);

					Class<?>[] parameters = { int.class, int.class, int.class,
							boolean.class };
					Constructor<?> constr = clazz.getConstructor(parameters);
					Object object = constr.newInstance(bookId, numberOfReplies,
							id, executed);
					objects.add(object);

				}
			}

		} catch (IllegalArgumentException e) {
			logger.error("File not found. Check your input.", e);
		}
		return objects.toArray();
	}
}
