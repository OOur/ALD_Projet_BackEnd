package com.ald.projet.DAO;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import org.slf4j.LoggerFactory;

import com.ald.projet.entities.Artiste;
import com.ald.projet.entities.Oeuvre;
import com.ald.projet.simplified.ArtisteSimplifie;
import com.ald.projet.simplified.OeuvreSimplifiee;
import com.ald.projet.simplified.PeintureSimplifiee;
import com.ald.projet.simplified.PhotographieSimplifiee;
import com.ald.projet.simplified.SculptureSimplifiee;

public class ArtisteDAO extends GenericDAO {

	private static final org.slf4j.Logger LOG = LoggerFactory.getLogger(ArtisteDAO.class);

	public void testT(Artiste artiste) {
		EntityManager em = createEntityManager();
		EntityTransaction tx = null;
		try {
			tx = em.getTransaction();
			tx.begin();
			Artiste a =  em.find(Artiste.class,artiste.getId());


			for (Oeuvre o : a.getOeuvres()) {
				LOG.info("info : "+ o.getTitre());
			}
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


	public List<ArtisteSimplifie> findAll() {
		List<ArtisteSimplifie> artistes = new ArrayList<ArtisteSimplifie>();
		List<Artiste> res = new ArrayList<Artiste>();
		EntityManager em = createEntityManager();

		res = em.createQuery("SELECT p FROM Artiste p").getResultList();
		for(Artiste a : res){
			artistes.add(new ArtisteSimplifie(a.getId(), a.getPrenom(), a.getNom()));
		}

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

	public Set<OeuvreSimplifiee> findOeuvresOfArtiste(int ArtisteId) {
		Set<OeuvreSimplifiee> oeuvres = new HashSet<OeuvreSimplifiee>();
		Set<Oeuvre> res = new HashSet<Oeuvre>();
		EntityManager em = createEntityManager();
		Artiste a = em.getReference(Artiste.class,ArtisteId);
		res = a.getOeuvres();

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
