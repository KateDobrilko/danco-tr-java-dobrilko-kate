package com.danco.training.dobrilko.action.reply;

import java.util.InputMismatchException;

import org.apache.log4j.Logger;

import com.danco.training.dobrilko.action.IAction;
import com.danco.training.dobrilko.ioutil.SpecificIOFeaturesUtil;
import com.danco.training.dobrilko.ui.icontrollerinstance.ControllerHolder;

public class AddReplyAction implements IAction {
	public void execute() {
		try {
			String reply = SpecificIOFeaturesUtil.readReply();
			ControllerHolder.getInstance().getController().addReply(reply);
		} catch (InputMismatchException e) {
			Logger logger = Logger.getLogger(AddReplyAction.class);
			logger.error("Input mismatch!");
		}
	}
}
