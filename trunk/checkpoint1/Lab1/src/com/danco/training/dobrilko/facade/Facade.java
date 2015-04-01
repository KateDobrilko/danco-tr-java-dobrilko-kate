package com.danco.training.dobrilko.facade;

import java.util.ArrayList;
import com.danco.training.dobrilko.file.FileWorker;
import com.danco.training.dobrilko.number.Summarizer;
import com.danco.training.dobrilko.property.PropertyStorage;
import com.danco.training.dobrilko.view.TextWorkerUtil;

public class Facade {

	public void performAllWork() {
		FileWorker fw = new FileWorker();
		String input = fw.readFromFile(PropertyStorage.getInstance()
				.getReadPath());
		ArrayList<Integer> list = TextWorkerUtil.parse(input);
		Integer value = Summarizer.getSum(list);
		fw.writeInFile(PropertyStorage.getInstance().getWritePath(),
				value.toString());
	}
}
