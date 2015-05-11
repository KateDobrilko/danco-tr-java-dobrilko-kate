package com.danco.training.dobrilko.ModelDB.dao;

import com.danco.training.dobrilko.ModelDB.abstractdao.AbstractHibernateDAO;
import com.danco.training.dobrilko.ModelDB.entity.Order;

public class HibernateOrderDAO extends AbstractHibernateDAO<Order, Integer> {

	public HibernateOrderDAO() {
		super(Order.class);
	}

}
