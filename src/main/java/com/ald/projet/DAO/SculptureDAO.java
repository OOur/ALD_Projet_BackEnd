package com.ald.projet.DAO;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import org.slf4j.LoggerFactory;

import com.ald.projet.entities.Sculpture;

public class SculptureDAO extends GenericDAO {
	
private static final org.slf4j.Logger LOG = LoggerFactory.getLogger(SculptureDAO.class);
	
	public void createSculpture(Sculpture sculpture) {
		EntityManager em = createEntityManager();
		EntityTransaction tx = null;
		try {
			tx = em.getTransaction();
			tx.begin();
			em.persist(sculpture);
			tx.commit();


		} catch (Exception re) {
			if (tx != null){
				System.err.println("Something went wrong");
				LOG.error("create failed", re);
			}
			tx.rollback();
		}

	}
	
	public void updateSculpture(Sculpture sculpture){
		EntityManager em = createEntityManager();
		EntityTransaction tx = null;
		try {
			tx = em.getTransaction();
			tx.begin();
			em.merge(sculpture);
			tx.commit();


		} catch (Exception re) {
			if (tx != null)
				System.err.println("Something went wrong");
			tx.rollback();
		}
	}



	public List<Sculpture> findAll() {
		List<Sculpture> sculptures = new ArrayList<Sculpture>();
		EntityManager em = createEntityManager();

		sculptures = em.createQuery("SELECT p FROM Sculpture p").getResultList();
		return sculptures;
	}

	public Sculpture findById(int id){
		EntityManager em = createEntityManager();
		Sculpture Sculpture = em.find(Sculpture.class,id);
		return Sculpture;
	}

	public void deleteSculpture(Sculpture persistentInstance){
		EntityManager em = createEntityManager();
		em.remove(persistentInstance);
	}
}
