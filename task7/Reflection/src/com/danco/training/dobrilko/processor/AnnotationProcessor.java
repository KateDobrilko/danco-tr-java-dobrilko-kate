package com.danco.training.dobrilko.processor;

import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

import com.danco.training.dobrilko.annotation.CSVCompositeList;
import com.danco.training.dobrilko.annotation.CSVEntityList;
import com.danco.training.dobrilko.annotation.CSVNonCompositeObjectProperty;
import com.danco.training.dobrilko.annotation.CSVPrimitiveProperty;
import com.danco.training.dobrilko.comparator.CSVPrimitivePropertyPositionComparator;
import com.danco.training.dobrilko.interfaceholder.HasId;

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
	@SuppressWarnings("deprecation")
	public void writeInFile(Object[] objects) throws IllegalArgumentException,
			IOException, IllegalAccessException {
		
			CSVEntityList csvEntity = objects.getClass().getAnnotation(
					CSVEntityList.class);
		FileWriter writer = new FileWriter(csvEntity.fileName(), true);
		StringBuilder sb = new StringBuilder();
		for (Object object : objects) {
			

				Field[] fields = object.getClass().getFields();
				
				CSVPrimitiveProperty[] cpp = object.getClass()
						.getAnnotationsByType(CSVPrimitiveProperty.class);
				Arrays.sort(cpp, new CSVPrimitivePropertyPositionComparator());
				for (CSVPrimitiveProperty cp : cpp) {
					for (Field field : fields) {
						if (field.getAnnotation(CSVPrimitiveProperty.class)
								.equals(cp)) {
							Class<?> type = field.getType();
							if (type.equals(Integer.class)) {
								Integer value = 0;
								field.getInt(value);
								sb.append(value);
							}
							if (type.equals(Double.class)) {
								Double value = 0.0;
								field.getDouble(value);
								sb.append(value);
							}

							if (type.equals(Boolean.class)) {
								Boolean value = false;
								field.getBoolean(value);
								sb.append(value);
							}
							if (type.equals(String.class)) {
								String value = "";
								field.get(value);
								sb.append(value);
							}
							if (type.equals(Date.class)) {
								Date value = new Date();
								field.get(value);
								sb.append(value.getDate()+";"+value.getMonth()+";"+value.getYear()+";");
							}

							sb.append(csvEntity.valuesSeparator());
						}
						sb.append(System.lineSeparator());
					}
				}
				for (Field field : fields) {
					if (field.isAnnotationPresent(CSVCompositeList.class)) {
						Class<?> type = field.getType();
						if (type.equals(ArrayList.class)) {

							ArrayList<Object> list = new ArrayList<Object>();
							field.get(list);

							for (Object obj : list) {
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
						cpp = type
								.getAnnotationsByType(
										CSVPrimitiveProperty.class);
						Arrays.sort(cpp,
								new CSVPrimitivePropertyPositionComparator());
						for (CSVPrimitiveProperty cp : cpp) {
							for (Field f : fields) {
								if (field.getAnnotation(
										CSVPrimitiveProperty.class).equals(cp)) {
									Class<?> type1 = f.getType();
									if (type1.equals(Integer.class)) {
										Integer value = 0;
										f.getInt(value);
										sb.append(value);
									}
									if (type1.equals(Double.class)) {
										Double value = 0.0;
										f.getDouble(value);
										sb.append(value);
									}

									if (type1.equals(Boolean.class)) {
										Boolean value = false;
										f.getBoolean(value);
										sb.append(value);
									}
									if (type1.equals(String.class)) {
										String value = "";
										f.get(value);
										sb.append(value);
									}
									if (type1.equals(Date.class)) {
										Date value = new Date();
										f.get(value);
										sb.append(value.getDate()+";"+value.getMonth()+";"+value.getYear()+";");
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

