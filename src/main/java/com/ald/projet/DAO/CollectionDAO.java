package com.ald.projet.DAO;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import org.slf4j.LoggerFactory;

import com.ald.projet.entities.Collection;
import com.ald.projet.entities.Oeuvre;

public class CollectionDAO extends GenericDAO {

	private static final org.slf4j.Logger LOG = LoggerFactory.getLogger(CollectionDAO.class);
	
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
				LOG.error("create collection failed", re);
			tx.rollback();
		}

	}

	public void updateCollection(Collection collection){
		EntityManager em = createEntityManager();
		EntityTransaction tx = null;
		try {
			tx = em.getTransaction();
			tx.begin();
			em.merge(collection);
			tx.commit();


		} catch (Exception re) {
			if (tx != null)
				LOG.error("update collection failed", re);
			tx.rollback();
		}
	}


	public List<Collection> findAll() {
		List<Collection> collections = new ArrayList<Collection>();
		EntityManager em = createEntityManager();

		collections = em.createQuery("SELECT p FROM Collection p").getResultList();
		return collections;
	}

	
	public List<Oeuvre> getOeuvresOfCollection(int collectionId) {
		List<Oeuvre> oeuvres = new ArrayList<Oeuvre>();
		EntityManager em = createEntityManager();
		//different de find, ne consulte pas la base de donnees => retourne un proxy.
		Collection c = em.getReference(Collection.class,collectionId);
		//comme on tente d'acceder aux oeuvres de la collection => interroge la base de donnes
		oeuvres = c.getOeuvres();
		
		return oeuvres;
	}


	public Collection findById(int id){
		EntityManager em = createEntityManager();
		Collection artiste = em.find(Collection.class,id);
		return artiste;
	}

	public void deleteCollection(Collection persistentInstance){
		EntityManager em = createEntityManager();
		EntityTransaction tx = null;
		try {
			tx = em.getTransaction();
			tx.begin();
			em.remove(persistentInstance);
			tx.commit();


		} catch (Exception re) {
			if (tx != null)
				LOG.error("delete collection failed", re);
			tx.rollback();
		}
	}
	
		
	
}
