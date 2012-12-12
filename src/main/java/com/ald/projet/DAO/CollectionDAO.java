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
				LOG.error("remove oeuvre failed", re);
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
				LOG.error("remove oeuvre failed", re);
			tx.rollback();
		}
	}


	public List<Collection> findAll() {
		List<Collection> collections = new ArrayList<Collection>();
		EntityManager em = createEntityManager();

		collections = em.createQuery("SELECT p FROM Collection p").getResultList();
		return collections;
	}

	public List<Oeuvre> findAllOeuvreOfCollection(int collectionId) {
		List<Oeuvre> oeuvres = new ArrayList<Oeuvre>();
		EntityManager em = createEntityManager();

		oeuvres = em.createQuery("Select o from Oeuvre o inner join o.Collection_oeuvre c where c.collection_id =: id").setParameter("id", collectionId).getResultList();
		return oeuvres;
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
