package com.ald.projet.DAO;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import com.ald.projet.entities.Peinture;

public class PeintureDAO extends GenericDAO {



	public void createPeinture(Peinture peinture) {
		EntityManager em = createEntityManager();
		EntityTransaction tx = null;
		try {
			tx = em.getTransaction();
			tx.begin();
			em.persist(peinture);
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
