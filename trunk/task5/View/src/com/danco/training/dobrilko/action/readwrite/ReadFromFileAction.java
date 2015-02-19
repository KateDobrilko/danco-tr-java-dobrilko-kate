package com.danco.training.dobrilko.action.readwrite;

import com.danco.training.dobrilko.action.IAction;

import com.danco.training.dobrilko.controller.SingletonBookShopController;
import com.danco.training.dobrilko.ioutil.IOUtil;

public class ReadFromFileAction  implements IAction {
    public void execute() {
	IOUtil.print("Store Path;");
	String SPath =  IOUtil.readString(); 
        IOUtil.print("Ordering System Path;");
        String OSPath =  IOUtil.readString();
        IOUtil.print("Reply System Path:");
        String RSPath =  IOUtil.readString();
        
       
       
	SingletonBookShopController.getInstance().readFromFile(SPath, OSPath, RSPath);
    }
}
