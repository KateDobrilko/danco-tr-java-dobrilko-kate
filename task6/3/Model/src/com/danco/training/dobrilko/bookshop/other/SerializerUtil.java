package com.danco.training.dobrilko.bookshop.other;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import com.danco.training.dobrilko.bookshop.service.BookShop;

public class SerializerUtil {
	public static void WriteInFile(String path, BookShop bs) {
		FileOutputStream fos;
		try {
			fos = new FileOutputStream(path);

			ObjectOutputStream oos;

			oos = new ObjectOutputStream(fos);
			oos.writeObject(bs);
			oos.flush();
			oos.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void ReadFromFile(String path, BookShop bs) {

		try {
			FileInputStream fis = new FileInputStream(path);
			ObjectInputStream oin = new ObjectInputStream(fis);
			bs = (BookShop) oin.readObject();
			oin.close();
		} catch (ClassNotFoundException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
