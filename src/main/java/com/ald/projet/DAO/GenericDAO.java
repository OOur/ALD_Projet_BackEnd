package com.ald.projet.DAO;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.ald.projet.filters.JPAUtil;

public abstract class GenericDAO {
	private EntityManager entityManager;
	private JPAUtil util;

	public EntityManager createEntityManager() {
//		util = new JPAUtil();
//		entityManager = JPAUtil.getEntityManager();
//		return entityManager;
		
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("Musee");
				entityManager = entityManagerFactory.createEntityManager();
				return entityManager;
	}

	public void closeEntityManager() {
		entityManager.close();
	}
}
