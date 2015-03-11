package com.danco.training.dobrilko.processor;

import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;

import com.danco.training.dobrilko.annotation.CSVCompositeList;
import com.danco.training.dobrilko.annotation.CSVEntity;
import com.danco.training.dobrilko.annotation.CSVNonCompositeObjectProperty;
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
		String fileName="";
		if(csvEntity.csvPath()==CSVFileReflectionPath.BookReflectionPath){
			fileName = PropertyStorage.getInstance().getCSVBookReflectionFilePath();
		}
		if(csvEntity.csvPath()==CSVFileReflectionPath.OrderReflectionPath){
			fileName = PropertyStorage.getInstance().getCSVOrderReflectionFilePath();
		}
		if(csvEntity.csvPath()==CSVFileReflectionPath.ReplyReflectionPath){
			fileName = PropertyStorage.getInstance().getCSVReplyReflectionFilePath();
		}
		FileWriter writer = new FileWriter(fileName, true);
		StringBuilder sb = new StringBuilder();
		for (Object object : objects) {

			Field[] fields = object.getClass().getDeclaredFields();
			ArrayList<CSVPrimitiveProperty> cpp = new ArrayList<CSVPrimitiveProperty>();
			for (Field field : fields) {
				if (field.isAnnotationPresent(CSVPrimitiveProperty.class)) {
					cpp.add(field.getAnnotation(CSVPrimitiveProperty.class));
				}
			}

			Collections.sort(cpp, new CSVPrimitivePropertyPositionComparator());
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
									sb.append(value.getDate()
											+ csvEntity.valuesSeparator()
											+ value.getMonth()
											+ csvEntity.valuesSeparator()
											+ value.getYear()
											+ csvEntity.valuesSeparator());
								}
							}

						}
						
					}
					
				}
				sb.append(System.lineSeparator());
				break;
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
								sb.append(((HasId) obj).getId());
								sb.append(csvEntity.valuesSeparator());
							}
						}
					}
					sb.append(System.lineSeparator());
				}

				if (field
						.isAnnotationPresent(CSVNonCompositeObjectProperty.class)) {
					Class<?> type = field.getType();
					fields = type.getFields();
					for (Field f : fields) {
						if (f.isAnnotationPresent(CSVPrimitiveProperty.class)) {
							cpp.add(f.getAnnotation(CSVPrimitiveProperty.class));
						}
					}
					Collections.sort(cpp,
							new CSVPrimitivePropertyPositionComparator());
					for (CSVPrimitiveProperty cp : cpp) {
						for (Field f : fields) {
							if (field.getAnnotation(CSVPrimitiveProperty.class)
									.equals(cp)) {
								Class<?> type1 = f.getType();
								if (type1.equals(Integer.class)) {
									Integer value = 0;
									Field newField = object.getClass()
											.getDeclaredField(f.getName());
									newField.setAccessible(true);
									value = (Integer) newField.get(object);

									sb.append(value);
								}
								if (type1.equals(Double.class)) {
									Double value = 0.0;

									Field newField = object.getClass()
											.getDeclaredField(f.getName());
									newField.setAccessible(true);
									value = (Double) newField.get(object);

									sb.append(value);
									sb.append(csvEntity.valuesSeparator());
								}

								if (type1.equals(Boolean.class)) {
									Boolean value = false;
									Field newField = object.getClass()
											.getDeclaredField(f.getName());
									newField.setAccessible(true);
									value = (Boolean) newField.get(object);

									sb.append(value);
									sb.append(csvEntity.valuesSeparator());
								}
								if (type1.equals(String.class)) {
									String value = "";
									Field newField = object.getClass()
											.getDeclaredField(f.getName());
									newField.setAccessible(true);
									value = (String) newField.get(object);
									sb.append(value);
								}
								if (type1.equals(Date.class)) {
									Date value = new Date();
									Field newField = object.getClass()
											.getDeclaredField(f.getName());
									newField.setAccessible(true);
									value = (Date) newField.get(object);
									if (value == null) {

									} else {
										sb.append(value.getDate()
												+ csvEntity.valuesSeparator()
												+ value.getMonth()
												+ csvEntity.valuesSeparator()
												+ value.getYear()
												+ csvEntity.valuesSeparator());
									}
									sb.append(csvEntity.valuesSeparator());
								}

								sb.append(csvEntity.valuesSeparator());
							}
							sb.append(System.lineSeparator());
						}
					}

				}

			}

		}
		writer.append(sb.toString());
		writer.flush();
		writer.close();
	}
}
