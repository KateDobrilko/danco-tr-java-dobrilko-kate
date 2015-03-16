package com.danco.training.dobrilko.action.reply;

import java.util.InputMismatchException;

import org.apache.log4j.Logger;

import com.danco.training.dobrilko.action.IAction;
import com.danco.training.dobrilko.controller.BookshopController;
import com.danco.training.dobrilko.entity.Reply;
import com.danco.training.dobrilko.ioutil.SpecificIOFeaturesUtil;

public class AddReplyAction implements IAction {
	public void execute() {
		try {
			Reply reply = SpecificIOFeaturesUtil.readReply();
			BookshopController.addReply(reply);
		} catch (InputMismatchException e) {
			Logger logger = Logger.getLogger(AddReplyAction.class);
			logger.error("Input mismatch!");
		}
	}
}
