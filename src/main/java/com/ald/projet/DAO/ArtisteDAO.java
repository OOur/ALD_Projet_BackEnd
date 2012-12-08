package com.ald.projet.DAO;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import com.ald.projet.entities.Artiste;
import com.ald.projet.entities.Peinture;

public class ArtisteDAO extends GenericDAO {
	
	
	public void createArtiste(Artiste artiste) {
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

	public void updateArtsite(Artiste artiste){
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


	public List<Artiste> findAll() {
		List<Artiste> artistes = new ArrayList<Artiste>();
		EntityManager em = createEntityManager();

		artistes = em.createQuery("SELECT p FROM Artiste p").getResultList();
		return artistes;
	}

	public Artiste findById(int id){
		EntityManager em = createEntityManager();
		Artiste artiste = em.find(Artiste.class,id);
		return artiste;
	}

	public void deletePeinture(Artiste persistentInstance){
		EntityManager em = createEntityManager();
		em.remove(persistentInstance);
	}
}
