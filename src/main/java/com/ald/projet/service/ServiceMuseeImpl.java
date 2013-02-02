package com.ald.projet.service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

import org.slf4j.LoggerFactory;

import com.ald.projet.DAO.ArtisteDAO;
import com.ald.projet.DAO.CollectionDAO;
import com.ald.projet.DAO.ConnexionDAO;
import com.ald.projet.DAO.OeuvreDAO;
import com.ald.projet.DAO.PhotoDAO;
import com.ald.projet.DAO.ReproductionDAO;
import com.ald.projet.entities.Artiste;
import com.ald.projet.entities.Collection;
import com.ald.projet.entities.Employe;
import com.ald.projet.entities.Oeuvre;
import com.ald.projet.entities.Photo;
import com.ald.projet.entities.Reproduction;
import com.ald.projet.property.Connexion;
import com.ald.projet.simplified.ArtisteSimplifie;
import com.ald.projet.simplified.CollectionSimplifiee;
import com.ald.projet.simplified.OeuvreSimplifiee;


public class ServiceMuseeImpl implements ServiceMusee {

	private static final org.slf4j.Logger LOG = LoggerFactory.getLogger(ServiceMuseeImpl.class);	
	private static CollectionDAO collectionDAO = new CollectionDAO();
	private static OeuvreDAO oeuvreDAO = new OeuvreDAO();
	private static ArtisteDAO artisteDAO = new ArtisteDAO();
	private static ReproductionDAO reproductionDAO = new ReproductionDAO();
	private static PhotoDAO photoDAO = new PhotoDAO();
	private static ConnexionDAO connexionDAO = new ConnexionDAO();


	public ServiceMuseeImpl(){

	}



	public Response connection(Connexion connexion){
		LOG.info("login "+connexion.getLogin()+ " mdp "+connexion.getPassword());
		String status = connexionDAO.isValidConnection(connexion);
		/**
		 * erreur 400 : Could not find message body reader for type: class com.ald.projet.property.Connexion of content type: application/x-www-form-urlencoded. 
		 * Dès que je décommente "Connexion connexion" pour l'interpréter depuis la requête HTTP reçue ça plante. 
		 * Le contenu renvoyé par le client n'est pas au format XML mais une sorte de contenu de formulaire
		 * En attendant, je retourne toujours le status "Conservateur" pour pouvoir avancer. 
		 */
		String test = "Conservateur";
		return Response.ok(status).build();

	}


	
	public Response createCollection(Collection collection){
		collectionDAO.createCollection(collection);
		return Response.ok(collection).build();
	}


	
	public Response createOeuvre(Oeuvre o){
		oeuvreDAO.createOeuvre(o);
		return Response.ok(o).build();
	}

	
	public Response createReproduction(Reproduction r){
		reproductionDAO.createReproduction(r);
		return Response.ok(r).build();
	}

	
	public Response createPhoto(Photo p){
		photoDAO.createPhoto(p);
		return Response.ok(p).build();
	}


	public Response createArtiste(Artiste a){
		artisteDAO.createArtiste(a);
		return Response.ok(a).build();
	}

	
	public Response createEmploye(Employe e){
		connexionDAO.createEmploye(e);
		return Response.ok(e).build();
	}


	public Response updateOeuvre(Oeuvre o){
		oeuvreDAO.updateOeuvre(o);
		return Response.ok(o).build();
	}


	public Response updateCollection(Collection collection){
		collectionDAO.updateCollection(collection);
		return Response.ok(collection).build();
	}

	
	public Collection displayCollection(@PathParam("id") int CollectionId){
		Collection collection = collectionDAO.findById(CollectionId);
		return collection;
	}

	
	public List<CollectionSimplifiee> getCollections(){
		List<CollectionSimplifiee> collection = collectionDAO.findAll();
		return collection;
	}
	
	
	public void deleteCollection(@PathParam("id")int id) {
		Collection collection = collectionDAO.findById(id);
		collectionDAO.deleteCollection(collection);			
	}

	
	public List<OeuvreSimplifiee> getAllOeuvres(){
		List<OeuvreSimplifiee> oeuvres = oeuvreDAO.findAll();
		return oeuvres;

	}

	
	public Oeuvre getOeuvre(@PathParam("id")int id){
		Oeuvre oeuvre = oeuvreDAO.findById(id);
		return oeuvre;
	}


	public List<Oeuvre> getCriteriaOeuvre(Oeuvre oeuvre){
		List<Oeuvre>  oeuvres = oeuvreDAO.findByCriteria(oeuvre);
		return oeuvres;
	}
	
	
	public void deleteOeuvre(@PathParam("id")int id) {
		Oeuvre oeuvre = oeuvreDAO.findById(id);
		oeuvreDAO.removeOeuvre(oeuvre);			
	}
	
	
	public void deleteArtiste(@PathParam("id")int id) {
		Artiste artiste = artisteDAO.findById(id);
		artisteDAO.deleteArtiste(artiste);			
	}


	public Artiste getArtiste(@PathParam("id")int id){
		Artiste a = artisteDAO.findById(id);
		return a;
	}


	public Set<OeuvreSimplifiee> findOeuvresOfArtiste(@PathParam("id")int ArtisteId)
	{
		Set<OeuvreSimplifiee> oeuvres = new HashSet<OeuvreSimplifiee>();
		oeuvres = artisteDAO.findOeuvresOfArtiste(ArtisteId);
		return oeuvres;	
	}

	
	public List<ArtisteSimplifie> getArtistes(){
		List<ArtisteSimplifie> a = artisteDAO.findAll();
		return a;
	}


	public List<OeuvreSimplifiee> getAllOeuvreReproduced() {
		List<OeuvreSimplifiee> oeuvres = oeuvreDAO.findOeuvresReproduced();
		return oeuvres;
	}
	

	public List<OeuvreSimplifiee> getAllOeuvreNotReproduced() {
		List<OeuvreSimplifiee> oeuvres = oeuvreDAO.findOeuvresNotReproduced();
		return oeuvres;
	}



	public List<Reproduction> getReproductionsOfOeuvre(int id) {
		List<Reproduction> reproductions = new ArrayList<Reproduction>();
		reproductions = reproductionDAO.getAllReproductionOfOeuvre(id);
		return reproductions;
	}



	public long getNbReproductionOfOeuvre(int id) {
		return reproductionDAO.getNbReproductionOfOeuvre(id);
	}



	public double setReproductionPrice(int id, double price) {
		Reproduction reproduction = reproductionDAO.findById(id);
		reproduction.setPrix(price);
		reproductionDAO.updateReproduction(reproduction);
		return reproduction.getPrix();
	}



	public List<Reproduction> getReproductionsOfCollection(int id) {
		List<Reproduction> reproductions = reproductionDAO.getAllReproductionOfCollection(id);
		return reproductions;
	}



	public List<OeuvreSimplifiee> getOeuvresOfCollectionNeverReproduced(int id) {
		List<OeuvreSimplifiee> oeuvres = reproductionDAO.getOeuvresOfCollectionNeverReproduced(id);
		return oeuvres;
	}



	
	
	
	
}

