package com.ald.projet.DAO;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import com.ald.projet.entities.Artiste;
import com.ald.projet.entities.Collection;

public class CollectionDAO extends GenericDAO {
	
	public void createCollection(Collection artiste) {
		EntityManager em = createEntityManager();
		EntityTransaction tx = null;
		try {
			tx = em.getTransaction();
			tx.begin();
			em.persist(artiste);
			tx.commit();


		} catch (Exception re) {
			if (tx != null)
				System.err.println("Something went wrong");
			tx.rollback();
		}

	}

	public void updateCollection(Collection artiste){
		EntityManager em = createEntityManager();
		EntityTransaction tx = null;
		try {
			tx = em.getTransaction();
			tx.begin();
			em.merge(artiste);
			tx.commit();


		} catch (Exception re) {
			if (tx != null)
				System.err.println("Something went wrong");
			tx.rollback();
		}
	}


	public List<Collection> findAll() {
		List<Collection> artistes = new ArrayList<Collection>();
		EntityManager em = createEntityManager();

		artistes = em.createQuery("SELECT p FROM Collection p").getResultList();
		return artistes;
	}

	public Collection findById(int id){
		EntityManager em = createEntityManager();
		Collection artiste = em.find(Collection.class,id);
		return artiste;
	}

	public void deleteCollection(Collection persistentInstance){
		EntityManager em = createEntityManager();
		em.remove(persistentInstance);
	}
}
