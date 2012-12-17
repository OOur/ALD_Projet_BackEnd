package com.ald.projet.DAO;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import org.slf4j.LoggerFactory;

import com.ald.projet.entities.Artiste;
import com.ald.projet.entities.Oeuvre;
import com.ald.projet.entities.Oeuvre;

public class OeuvreDAO extends GenericDAO {


	private static final org.slf4j.Logger LOG = LoggerFactory.getLogger(OeuvreDAO.class);

	
	
	
	public void testT(Oeuvre o) {
		EntityManager em = createEntityManager();
		EntityTransaction tx = null;
		try {
			tx = em.getTransaction();
			tx.begin();
			Oeuvre oeuvre =  em.find(Oeuvre.class,o.getId());
			LOG.info("info : "+ oeuvre.getArtiste().getNom());
			LOG.info("info : "+ oeuvre.getArtiste().getPrenom());
			tx.commit();


		} catch (Exception re) {
			if (tx != null)
				LOG.error("test artiste failed", re);
			tx.rollback();
			
		}

	}
	
	
	public void createOeuvre(Oeuvre oeuvre) {
		EntityManager em = createEntityManager();
		EntityTransaction tx = null;
		try {
			tx = em.getTransaction();
			tx.begin();
			em.persist(oeuvre);
			tx.commit();


		} catch (Exception re) {
			if (tx != null){
				LOG.error("create oeuvre failed", re);
			}
			tx.rollback();
		}

	}

	public void updateOeuvre(Oeuvre oeuvre){
		EntityManager em = createEntityManager();
		EntityTransaction tx = null;
		try {
			tx = em.getTransaction();
			tx.begin();
			em.merge(oeuvre);
			tx.commit();


		} catch (Exception re) {
			if (tx != null)
				LOG.error("update oeuvre failed", re);
			tx.rollback();
		}
	}

	public void removeOeuvre(Oeuvre oeuvre){
		EntityManager em = createEntityManager();
		EntityTransaction tx = null;
		try {
			tx = em.getTransaction();
			tx.begin();
			// rattache l'ojbet a l'entity manager et le supprime
			em.remove(em.merge(oeuvre));
			tx.commit();


		} catch (Exception re) {
			if (tx != null)
				LOG.error("remove oeuvre failed", re);
			tx.rollback();
		}
	}



	public List<Oeuvre> findAll() {
		List<Oeuvre> oeuvres = new ArrayList<Oeuvre>();
		EntityManager em = createEntityManager();

		oeuvres = em.createQuery("SELECT p FROM Oeuvre p").getResultList();
		return oeuvres;
	}

	public Oeuvre findById(int id){
		EntityManager em = createEntityManager();
		Oeuvre oeuvre = em.find(Oeuvre.class,id);
		return oeuvre;
	}

	public void deleteOeuvre(Oeuvre persistentInstance){
		EntityManager em = createEntityManager();
		EntityTransaction tx = null;
		try {
			tx = em.getTransaction();
			tx.begin();
			em.remove(persistentInstance);
			tx.commit();


		} catch (Exception re) {
			if (tx != null)
				LOG.error("delete oeuvre failed", re);
			tx.rollback();
		}
	}
}
