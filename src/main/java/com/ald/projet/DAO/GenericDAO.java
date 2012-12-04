package com.ald.projet.DAO;

import javax.persistence.EntityManager;

import com.ald.projet.filters.JPAUtil;

public abstract class GenericDAO {
	private EntityManager entityManager;
	private JPAUtil util;

	public EntityManager createEntityManager() {
		util = new JPAUtil();
		entityManager = JPAUtil.getEntityManager();
		return entityManager;
	}

	public void closeEntityManager() {
		entityManager.close();
	}
}
