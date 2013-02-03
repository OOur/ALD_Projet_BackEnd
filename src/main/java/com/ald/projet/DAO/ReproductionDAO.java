package com.ald.projet.DAO;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

import org.slf4j.LoggerFactory;

import com.ald.projet.entities.Collection;
import com.ald.projet.entities.Oeuvre;
import com.ald.projet.entities.Reproduction;
import com.ald.projet.simplified.OeuvreSimplifiee;
import com.ald.projet.simplified.PeintureSimplifiee;
import com.ald.projet.simplified.PhotographieSimplifiee;
import com.ald.projet.simplified.SculptureSimplifiee;

public class ReproductionDAO extends GenericDAO {


	private static final org.slf4j.Logger LOG = LoggerFactory.getLogger(ReproductionDAO.class);

	public void createReproduction(Reproduction reproduction) {
		EntityManager em = createEntityManager();
		EntityTransaction tx = null;
		try {
			tx = em.getTransaction();
			tx.begin();
			em.persist(reproduction);

			Oeuvre o = em.find(Oeuvre.class, reproduction.getOeuvre().getId());
			if(!o.hasBeenReproduced()){
				o.setHasBeenReproduced(true);
			}

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
		EntityTransaction tx = null;
		try {
			tx = em.getTransaction();
			tx.begin();
			em.remove(persistentInstance);
			tx.commit();


		} catch (Exception re) {
			if (tx != null)
				LOG.error("delete reproduction failed", re);
			tx.rollback();
		}
	}


	public List<Reproduction> getAllReproductionOfOeuvre(int id){
		List<Reproduction> reproductions = new ArrayList<Reproduction>();
		EntityManager em = createEntityManager();
		Oeuvre oeuvre = em.find(Oeuvre.class, id);

		Query q = em.createQuery("SELECT p FROM Reproduction p where p.oeuvre = :o");
		q.setParameter("o", oeuvre);
		reproductions = q.getResultList();

		return reproductions;
	}

	public long getNbReproductionOfOeuvre(int id) {
		EntityManager em = createEntityManager();
		Oeuvre oeuvre = em.find(Oeuvre.class, id);

		Query q = em.createQuery("SELECT count(p) FROM Reproduction p where p.oeuvre = :o");
		q.setParameter("o", oeuvre);
		long count = (Long) (q.getSingleResult());
		
		return count;
	}

	public List<Reproduction> getAllReproductionOfCollection(int id) {
		List<Reproduction> reproductions = new ArrayList<Reproduction>();
		EntityManager em = createEntityManager();
		Collection c = em.find(Collection.class, id);

		Query query = em.createQuery("SELECT c.oeuvres from Collection c where c =:c");
		query.setParameter("c", c);
		List<Oeuvre> oeuvres = query.getResultList();
		
		Query q = em.createQuery("SELECT r FROM Reproduction r WHERE r.oeuvre IN (:oeuvres)");
		q.setParameter("oeuvres", oeuvres);
		reproductions = q.getResultList();
		return reproductions;
		
		
	}

	public List<OeuvreSimplifiee> getOeuvresOfCollectionNeverReproduced(int id) {
		List<Oeuvre> res = new ArrayList<Oeuvre>();
		List<OeuvreSimplifiee> oeuvres = new ArrayList<OeuvreSimplifiee>();
		EntityManager em = createEntityManager();
		
		Query q = em.createQuery("select o from Oeuvre o where o IN (SELECT c FROM Collection c WHERE c.id = :id) and o.hasBeenReproduced = false");
		q.setParameter("id", id);
		res = q.getResultList();
		
		
		for(Oeuvre o : res){
			if(o.getClass().getName().contains("Sculpture")){
				oeuvres.add(new SculptureSimplifiee(o.getId(), o.getTitre(), o.hasBeenReproduced()));
			}else if(o.getClass().getName().contains("Peinture")){
				oeuvres.add(new PeintureSimplifiee(o.getId(), o.getTitre(), o.hasBeenReproduced()));
			}else if(o.getClass().getName().contains("Photographie")){
				oeuvres.add(new PhotographieSimplifiee(o.getId(), o.getTitre(), o.hasBeenReproduced()));
			}
		}
		return oeuvres;
	}

	public List<OeuvreSimplifiee> getOeuvresOfCollectionReproduced(int id) {
		List<Oeuvre> res = new ArrayList<Oeuvre>();
		List<OeuvreSimplifiee> oeuvres = new ArrayList<OeuvreSimplifiee>();
		EntityManager em = createEntityManager();
		
		Query q = em.createQuery("SELECT c.oeuvres FROM Collection c WHERE c.id = :id");
		q.setParameter("id", id);
		res = q.getResultList();

		
		Query q3 = em.createQuery("SELECT r.oeuvre FROM Reproduction r WHERE r.oeuvre IN (:oeuvres)");
		q3.setParameter("oeuvres", res);
		res = q3.getResultList();
		
		
		for(Oeuvre o : res){
			if(o.getClass().getName().contains("Sculpture")){
				oeuvres.add(new SculptureSimplifiee(o.getId(), o.getTitre(), o.hasBeenReproduced()));
			}else if(o.getClass().getName().contains("Peinture")){
				oeuvres.add(new PeintureSimplifiee(o.getId(), o.getTitre(), o.hasBeenReproduced()));
			}else if(o.getClass().getName().contains("Photographie")){
				oeuvres.add(new PhotographieSimplifiee(o.getId(), o.getTitre(), o.hasBeenReproduced()));
			}
		}
		return oeuvres;
	}
	
	
	public List<OeuvreSimplifiee> getOeuvresOfCollectionNotReproduced(int id) {
		List<Oeuvre> res = new ArrayList<Oeuvre>();
		
		List<OeuvreSimplifiee> oeuvres = new ArrayList<OeuvreSimplifiee>();
		EntityManager em = createEntityManager();
		
		Query q = em.createQuery("SELECT r.oeuvre FROM Reproduction r");
		res = q.getResultList();

		Query q2 = em.createQuery(" SELECT c.oeuvres FROM Collection c WHERE c.id = :id");
		q2.setParameter("id", id);
		List<Oeuvre> res2 = q2.getResultList();
		
		Query q3 = em.createQuery("SELECT o FROM Oeuvre o WHERE o IN (:oeuvres) AND o NOT IN (:reproductions)");
		q3.setParameter("reproductions", res);
		q3.setParameter("oeuvres", res2);
		res = q3.getResultList();
		
		
		for(Oeuvre o : res){
			if(o.getClass().getName().contains("Sculpture")){
				oeuvres.add(new SculptureSimplifiee(o.getId(), o.getTitre(), o.hasBeenReproduced()));
			}else if(o.getClass().getName().contains("Peinture")){
				oeuvres.add(new PeintureSimplifiee(o.getId(), o.getTitre(), o.hasBeenReproduced()));
			}else if(o.getClass().getName().contains("Photographie")){
				oeuvres.add(new PhotographieSimplifiee(o.getId(), o.getTitre(), o.hasBeenReproduced()));
			}
		}
		return oeuvres;
	}

}
