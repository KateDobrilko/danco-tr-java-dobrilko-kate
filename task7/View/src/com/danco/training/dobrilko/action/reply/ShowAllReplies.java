package com.danco.training.dobrilko.action.reply;

import com.danco.training.dobrilko.action.IAction;
import com.danco.training.dobrilko.controller.BookshopController;
import com.danco.training.dobrilko.entitiy.Reply;
import com.danco.training.dobrilko.ioutil.IOUtil;
import com.danco.training.dobrilko.ioutil.SpecificIOFeaturesUtil;

public class ShowAllReplies implements IAction {
	public void execute() {
		for (Reply reply : BookshopController.getReplies()) {
			SpecificIOFeaturesUtil.printReply(reply);
			IOUtil.println();
		}
	}
}
