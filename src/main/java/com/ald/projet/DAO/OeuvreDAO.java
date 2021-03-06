package com.ald.projet.DAO;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.slf4j.LoggerFactory;

import com.ald.projet.entities.Collection;
import com.ald.projet.entities.Oeuvre;
import com.ald.projet.simplified.OeuvreSimplifiee;
import com.ald.projet.simplified.PeintureSimplifiee;
import com.ald.projet.simplified.PhotographieSimplifiee;
import com.ald.projet.simplified.SculptureSimplifiee;

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
		CollectionDAO co = new CollectionDAO();
		try {
			tx = em.getTransaction();
			tx.begin();

			//supprime les oeuvres dans les collections
			for (Collection c : getAllCollectionsOfOeuvre(oeuvre)) {
				Collection collection = co.findById(c.getId());
				collection.removeOeuvre(oeuvre);
			}
			em.remove(em.merge(oeuvre));
			tx.commit();


		} catch (Exception re) {
			if (tx != null)
				LOG.error("remove oeuvre failed", re);
			tx.rollback();
		}
	}



	public List<OeuvreSimplifiee> findAll() {
		List<OeuvreSimplifiee> oeuvres = new ArrayList<OeuvreSimplifiee>();
		List<Oeuvre> res = new ArrayList<Oeuvre>();
		EntityManager em = createEntityManager();

		res = em.createQuery("SELECT p FROM Oeuvre p").getResultList();

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

	public Oeuvre findById(int id){
		EntityManager em = createEntityManager();
		Oeuvre oeuvre = em.find(Oeuvre.class,id);
		return oeuvre;
	}

	/**
	 * 
	 * @param Oeuvre o. Cot� client, l'utilisateur remplira un formulaire pour rechercher toutes les oeuvres selon certains crit�res
	 * Les champs correspondent en fait aux attributs d'une oeuvre : une oeuvre interm�diaire sera donc cr��e de mani�re transparente 
	 * (mais pas persist�e, elle servira juste pour l'appel au web service). Les champs qui auront �t� reseign�s seront les crit�res de 
	 * la requ�te, ceux qui n'auront pas �t� renseign� seront null et donc ils ne seront pas int�gr�s � la requ�te.
	 * ==> Construction de requ�te dynamique.
	 * @return un DTO contenant simplement une liste d'oeuvres.
	 */
	public List<Oeuvre> findByCriteria(Oeuvre o){

		List<Oeuvre> oeuvres = new ArrayList<Oeuvre>();

		EntityManager em = createEntityManager();
		EntityTransaction tx = null;

		CriteriaBuilder cb = em.getCriteriaBuilder();
		CriteriaQuery<Oeuvre> cq = cb.createQuery(Oeuvre.class);
		Root<Oeuvre> root = cq.from(Oeuvre.class); // FROM
		cq.select(root); //SELECT

		List<Predicate> predicateList = new ArrayList<Predicate>();

		if(o.getAnnee() != null){
			Predicate annee = cb.equal(root.get("annee"), o.getAnnee());
			predicateList.add(annee);
		}

		if(o.hasBeenReproduced() == true){
			Predicate reproduced = cb.equal(root.get("hasBeenReproduced"), o.hasBeenReproduced());
			predicateList.add(reproduced);
		}

		if(o.getArtiste() != null){
			Predicate artiste = cb.equal(root.get("artiste"), o.getArtiste());
			predicateList.add(artiste);
		}

		if(o.getTag() != null){
			Predicate tag = cb.equal(root.get("tag"), o.getTag());
			predicateList.add(tag);
		}

		Predicate[] predicates = new Predicate[predicateList.size()];
		predicateList.toArray(predicates);
		cq.where(predicates); //WHERE

		for(Oeuvre oeuvre: em.createQuery(cq).getResultList()){
			oeuvres.add(oeuvre);
		}
		
		return oeuvres;
	}




	public List<Collection> getAllCollectionsOfOeuvre(Oeuvre o){
		List<Collection> res = new ArrayList<Collection>();

		EntityManager em = createEntityManager();
		Query q = em.createQuery("select c from Collection c where :o MEMBER OF c.oeuvres");
		q.setParameter("o", o);
		res = q.getResultList();
		
		LOG.info("nb element = "+res.size());
		return res;
	}


	public List<OeuvreSimplifiee> findOeuvresNotReproduced() {
		List<Oeuvre> res = new ArrayList<Oeuvre>();
		List<OeuvreSimplifiee> oeuvres = new ArrayList<OeuvreSimplifiee>();
	
		
		EntityManager em = createEntityManager();
		Query q = em.createQuery("select DISTINCT o from Oeuvre o where o NOT IN (SELECT r.oeuvre FROM Reproduction r)");
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
	
	
	public List<OeuvreSimplifiee> findOeuvresReproduced() {
		List<Oeuvre> res = new ArrayList<Oeuvre>();
		List<OeuvreSimplifiee> oeuvres = new ArrayList<OeuvreSimplifiee>();
	
		
		EntityManager em = createEntityManager();
		Query q = em.createQuery("select DISTINCT o from Oeuvre o where o IN (SELECT r.oeuvre FROM Reproduction r)");
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


	
}
