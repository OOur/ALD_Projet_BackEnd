package com.ald.projet.DAO;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import org.slf4j.LoggerFactory;

import com.ald.projet.entities.AgentMusee;
import com.ald.projet.entities.Artiste;
import com.ald.projet.property.Connexion;

public class ConnexionDAO extends GenericDAO {

	private static final org.slf4j.Logger LOG = LoggerFactory.getLogger(ConnexionDAO.class);

	public boolean isValidConnection(Connexion connexion) {
		EntityManager em = createEntityManager();
		EntityTransaction tx = null;
		try {
			tx = em.getTransaction();
			tx.begin();
			Query q = em.createQuery("SELECT c FROM Conservateur c where c.connexion.login =:lo and c.connexion.password =:pa");
			q.setParameter("lo", connexion.getLogin());
			q.setParameter("pa", connexion.getPassword());
			AgentMusee agent = (AgentMusee) q.getSingleResult();
			tx.commit();
			if(agent != null){
				return true;
			}		
		} catch (Exception re) {
			if (tx != null)
				LOG.error("create artiste failed", re);
			LOG.error("create artiste failed", re);
			tx.rollback();

		}
		return false;
	}



}
