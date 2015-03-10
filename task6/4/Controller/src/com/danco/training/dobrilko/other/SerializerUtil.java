package com.danco.training.dobrilko.other;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import org.apache.log4j.Logger;

import com.danco.training.bookshop.Bookshop;

public class SerializerUtil {
	public static void WriteInFile(String path) {
		FileOutputStream fos;
		try {
			fos = new FileOutputStream(path);
			ObjectOutputStream oos;
			oos = new ObjectOutputStream(fos);
			oos.writeObject(Bookshop.getInstance());
			oos.flush();
			oos.close();
		} catch (IOException e) {
			Logger logger = Logger.getLogger(SerializerUtil.class);
			logger.error("IO Exception during writing operation has been detected.");
		}

	}

	public static void ReadFromFile(String path) {

		try {
			FileInputStream fis = new FileInputStream(path);
			ObjectInputStream oin = new ObjectInputStream(fis);
			Bookshop bs = (Bookshop) oin.readObject();
			Bookshop.getInstance().setBookBase(bs.getBookBase());
			Bookshop.getInstance().setOrderBase(bs.getOrderBase());
			Bookshop.getInstance().setReplyBase(bs.getReplyBase());

			oin.close();
		} catch (ClassNotFoundException | IOException e) {
			Logger logger = Logger.getLogger(SerializerUtil.class);
			logger.error("IO Exception during reading operation has been detected.");
		}

	}

}
