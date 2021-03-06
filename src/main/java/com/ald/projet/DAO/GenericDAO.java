package com.ald.projet.DAO;

import javax.persistence.EntityManager;

import org.slf4j.LoggerFactory;

import com.ald.projet.filters.JPAUtil;

public abstract class GenericDAO {
	private EntityManager entityManager;
	private static final org.slf4j.Logger LOG = LoggerFactory.getLogger(GenericDAO.class);	

	public EntityManager createEntityManager() {
		entityManager = JPAUtil.getEntityManager();
		return entityManager;
		
//		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("Musee");
//				entityManager = entityManagerFactory.createEntityManager();
//				return entityManager;
	}

	public void closeEntityManager() {
		entityManager.close();
	}
}
