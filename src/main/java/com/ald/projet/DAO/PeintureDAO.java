package com.ald.projet.DAO;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import org.slf4j.LoggerFactory;

import com.ald.projet.entities.Artiste;
import com.ald.projet.entities.Peinture;

public class PeintureDAO extends GenericDAO {

	private static final org.slf4j.Logger LOG = LoggerFactory.getLogger(PeintureDAO.class);
	
	public void createPeinture(Peinture peinture) {
		EntityManager em = createEntityManager();
		EntityTransaction tx = null;
		try {
			tx = em.getTransaction();
			tx.begin();
			em.persist(peinture);
			tx.commit();


		} catch (Exception re) {
			if (tx != null){
				System.err.println("Something went wrong");
				LOG.error("create failed", re);
			}
			tx.rollback();
		}

	}
	
	public void updatePeinture(Peinture peinture){
		EntityManager em = createEntityManager();
		EntityTransaction tx = null;
		try {
			tx = em.getTransaction();
			tx.begin();
			em.merge(peinture);
			tx.commit();


		} catch (Exception re) {
			if (tx != null)
				System.err.println("Something went wrong");
			tx.rollback();
		}
	}



	public List<Peinture> findAll() {
		List<Peinture> peintures = new ArrayList<Peinture>();
		EntityManager em = createEntityManager();

		peintures = em.createQuery("SELECT p FROM Peinture p").getResultList();
		return peintures;
	}

	public Peinture findById(int id){
		EntityManager em = createEntityManager();
		Peinture peinture = em.find(Peinture.class,id);
		return peinture;
	}

	public void deletePeinture(Peinture persistentInstance){
		EntityManager em = createEntityManager();
		em.remove(persistentInstance);
	}
}
