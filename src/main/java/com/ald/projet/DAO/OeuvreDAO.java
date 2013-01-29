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

import org.hibernate.Criteria;
import org.slf4j.LoggerFactory;

import com.ald.projet.entities.Artiste;
import com.ald.projet.entities.Employe;
import com.ald.projet.entities.Oeuvre;
import com.ald.projet.entities.Oeuvre;
import com.ald.projet.simplified.ArtisteSimplifie;
import com.ald.projet.simplified.OeuvreSimplifiee;
import com.ald.projet.simplified.OeuvresDTO;
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



	public List<OeuvreSimplifiee> findAll() {
		List<OeuvreSimplifiee> oeuvres = new ArrayList<OeuvreSimplifiee>();
		List<Oeuvre> res = new ArrayList<Oeuvre>();
		EntityManager em = createEntityManager();

		res = em.createQuery("SELECT p FROM Oeuvre p").getResultList();

		for(Oeuvre o : res){
			if(o.getClass().getName().contains("Sculpture")){
				oeuvres.add(new SculptureSimplifiee(o.getId(), o.getTitre()));
			}else if(o.getClass().getName().contains("Peinture")){
				oeuvres.add(new PeintureSimplifiee(o.getId(), o.getTitre()));
			}else if(o.getClass().getName().contains("Photographie")){
				oeuvres.add(new PhotographieSimplifiee(o.getId(), o.getTitre()));
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
	 * @param Oeuvre o. Coté client, l'utilisateur remplira un formulaire pour rechercher toutes les oeuvres selon certains critères
	 * Les champs correspondent en fait aux attributs d'une oeuvre : une oeuvre intermédiaire sera donc créée de manière transparente 
	 * (mais pas persistée, elle servira juste pour l'appel au web service). Les champs qui auront été reseignés seront les critères de 
	 * la requête, ceux qui n'auront pas été renseigné seront null et donc ils ne seront pas intégrés à la requête.
	 * ==> Construction de requête dynamique.
	 * @return un DTO contenant simplement une liste d'oeuvres.
	 */
	public OeuvresDTO findByCriteria(Oeuvre o){

		OeuvresDTO dto = new OeuvresDTO();

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

		dto.setOeuvre(em.createQuery(cq).getResultList());
		return dto;
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
