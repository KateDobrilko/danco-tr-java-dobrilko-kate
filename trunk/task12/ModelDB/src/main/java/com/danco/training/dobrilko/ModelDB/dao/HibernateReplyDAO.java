package com.danco.training.dobrilko.ModelDB.dao;

import com.danco.training.dobrilko.ModelDB.abstractdao.AbstractHibernateDAO;
import com.danco.training.dobrilko.ModelDB.entity.Reply;

public class HibernateReplyDAO extends AbstractHibernateDAO<Reply, Integer> {

	public HibernateReplyDAO() {
		super(Reply.class);
	}

}
