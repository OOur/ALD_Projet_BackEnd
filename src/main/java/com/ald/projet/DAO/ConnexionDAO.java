package com.ald.projet.DAO;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import org.slf4j.LoggerFactory;

import com.ald.projet.entities.Artiste;
import com.ald.projet.entities.Employe;
import com.ald.projet.property.Connexion;

public class ConnexionDAO extends GenericDAO {

	private static final org.slf4j.Logger LOG = LoggerFactory.getLogger(ConnexionDAO.class);

	public String isValidConnection(Connexion connexion) {
		EntityManager em = createEntityManager();
		EntityTransaction tx = null;
		try {
			tx = em.getTransaction();
			tx.begin();
			Query q = em.createQuery("SELECT c FROM Employe c where c.connexion.login =:lo and c.connexion.password =:pa");
			q.setParameter("lo", connexion.getLogin());
			q.setParameter("pa", connexion.getPassword());
			Employe employe = (Employe) q.getSingleResult();
			tx.commit();
			
			if(employe != null)
				return employe.getStatus().toString();
		} catch (Exception re) {
			LOG.error("connexion DAO failed", re);
			tx.rollback();

		}
		
		return null;
	}
	
	
	
	public void createEmploye(Employe employe) {
		EntityManager em = createEntityManager();
		EntityTransaction tx = null;
		try {
			tx = em.getTransaction();
			tx.begin();
			em.persist(employe);
			tx.commit();


		} catch (Exception re) {
			LOG.error("create employe failed", re);
			tx.rollback();

		}
	}



}
