package com.ald.projet.DAO;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import org.slf4j.LoggerFactory;

import com.ald.projet.entities.Artiste;
import com.ald.projet.entities.Peinture;
import com.ald.projet.filters.JPAUtil;

public class ArtisteDAO extends GenericDAO {
	
	private static final org.slf4j.Logger LOG = LoggerFactory.getLogger(ArtisteDAO.class);
	
	public void testT(Artiste artiste) {
		EntityManager em = createEntityManager();
		EntityTransaction tx = null;
		try {
			tx = em.getTransaction();
			tx.begin();
			Artiste a =  em.find(Artiste.class,artiste.getId());
			LOG.info("info : "+ a.getOeuvres().get(0).getTitre());
			tx.commit();


		} catch (Exception re) {
			if (tx != null)
				LOG.error("test artiste failed", re);
			tx.rollback();
			
		}

	}

	
	
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
				LOG.error("create artiste failed", re);
			LOG.error("create artiste failed", re);
			tx.rollback();
			
		}

	}

	public void updateArtiste(Artiste artiste){
		EntityManager em = createEntityManager();
		EntityTransaction tx = null;
		try {
			tx = em.getTransaction();
			tx.begin();
			em.merge(artiste);
			tx.commit();


		} catch (Exception re) {
			if (tx != null)
				LOG.error("update artiste failed", re);
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


	public void deleteArtiste(Artiste persistentInstance){
		EntityManager em = createEntityManager();
		EntityTransaction tx = null;
		try {
			tx = em.getTransaction();
			tx.begin();
			 em.remove(em.merge(persistentInstance));
			tx.commit();


		} catch (Exception re) {
			if (tx != null)
				LOG.error("delete artiste failed", re);
			tx.rollback();
		}
		
	}
}
