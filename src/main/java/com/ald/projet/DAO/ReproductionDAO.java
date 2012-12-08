package com.ald.projet.DAO;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import org.slf4j.LoggerFactory;

import com.ald.projet.entities.Reproduction;

public class ReproductionDAO extends GenericDAO {

	
private static final org.slf4j.Logger LOG = LoggerFactory.getLogger(ReproductionDAO.class);
	
	public void createReproduction(Reproduction reproduction) {
		EntityManager em = createEntityManager();
		EntityTransaction tx = null;
		try {
			tx = em.getTransaction();
			tx.begin();
			em.persist(reproduction);
			tx.commit();


		} catch (Exception re) {
			if (tx != null){
				LOG.error("create Reproduction failed", re);
			}
			tx.rollback();
		}

	}
	
	public void updateReproduction(Reproduction reproduction){
		EntityManager em = createEntityManager();
		EntityTransaction tx = null;
		try {
			tx = em.getTransaction();
			tx.begin();
			em.merge(reproduction);
			tx.commit();


		} catch (Exception re) {
			if (tx != null)
				LOG.error("update Reproduction failed", re);
			tx.rollback();
		}
	}
	
	public void removeReproduction(Reproduction reproduction){
		EntityManager em = createEntityManager();
		EntityTransaction tx = null;
		try {
			tx = em.getTransaction();
			tx.begin();
			// rattache l'ojbet a l'entity manager et le supprime
			em.remove(em.merge(reproduction));
			tx.commit();


		} catch (Exception re) {
			if (tx != null)
				LOG.error("remove Reproduction failed", re);
			tx.rollback();
		}
	}



	public List<Reproduction> findAll() {
		List<Reproduction> reproductions = new ArrayList<Reproduction>();
		EntityManager em = createEntityManager();

		reproductions = em.createQuery("SELECT p FROM Reproduction p").getResultList();
		return reproductions;
	}

	public Reproduction findById(int id){
		EntityManager em = createEntityManager();
		Reproduction reproduction = em.find(Reproduction.class,id);
		return reproduction;
	}

	public void deleteReproduction(Reproduction persistentInstance){
		EntityManager em = createEntityManager();
		em.remove(persistentInstance);
	}
}
