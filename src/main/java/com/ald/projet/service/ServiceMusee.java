package com.ald.projet.service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import org.slf4j.LoggerFactory;

import com.ald.projet.DAO.ArtisteDAO;
import com.ald.projet.DAO.CollectionDAO;
import com.ald.projet.DAO.ConnexionDAO;
import com.ald.projet.DAO.GenericDAO;
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
import com.ald.projet.simplified.OeuvreDTO;
import com.ald.projet.simplified.OeuvreSimplifiee;
import com.ald.projet.simplified.OeuvresDTO;

@Path("/service")
public class ServiceMusee {

	private static final org.slf4j.Logger LOG = LoggerFactory.getLogger(ServiceMusee.class);	
	private static CollectionDAO collectionDAO = new CollectionDAO();
	private static OeuvreDAO oeuvreDAO = new OeuvreDAO();
	private static ArtisteDAO artisteDAO = new ArtisteDAO();
	private static ReproductionDAO reproductionDAO = new ReproductionDAO();
	private static PhotoDAO photoDAO = new PhotoDAO();
	private static ConnexionDAO connexionDAO = new ConnexionDAO();


	public ServiceMusee(){

	}



	/*** Testé OK***/
	@POST
	@Path("/connexion")
	@Consumes("application/xml")
	//	@Consumes("application/x-www-form-urlencoded")
	@Produces("application/xml")
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


	/*** Testé OK***/
	@POST
	@Path("/collection/create")
	@Consumes("application/xml")
	@Produces("application/xml")
	public Response createCollection(Collection collection){
		collectionDAO.createCollection(collection);
		return Response.ok(collection).build();
	}


	/*** Testé OK***/
	@POST
	@Path("/oeuvre/create")
	@Consumes("application/xml")
	public Response createOeuvre(Oeuvre o){
		oeuvreDAO.createOeuvre(o);
		return Response.ok(o).build();
	}

	/*** Testé OK***/
	@POST
	@Path("/reproduction/create")
	@Consumes("application/xml")
	public Response createReproduction(Reproduction r){
		reproductionDAO.createReproduction(r);
		return Response.ok(r).build();
	}

	/*** Testé OK***/
	@POST
	@Path("/photo/create")
	@Consumes("application/xml")
	public Response createPhoto(Photo p){
		photoDAO.createPhoto(p);
		return Response.ok(p).build();
	}

	/*** Testé OK***/
	@POST
	@Path("/artiste/create")
	@Consumes("application/xml")
	public Response createArtiste(Artiste a){
		artisteDAO.createArtiste(a);
		return Response.ok(a).build();
	}

	/*** Testé OK***/
	@POST
	@Path("/employe/create")
	@Consumes("application/xml")
	public Response createEmploye(Employe e){
		connexionDAO.createEmploye(e);
		return Response.ok(e).build();
	}


	/*** Testé OK***/
	@POST
	@Path("/oeuvre/update")
	@Consumes("application/xml")
	public Response updateOeuvre(Oeuvre o){
		oeuvreDAO.updateOeuvre(o);
		return Response.ok(o).build();
	}

	/*** Testé OK***/
	@POST
	@Path("/collection/update")
	@Consumes("application/xml")
	public Response updateCollection(Collection collection){
		collectionDAO.updateCollection(collection);
		return Response.ok(collection).build();
	}

	/*** Testé OK***/
	@GET
	@Path("/collection/{id}")
	@Produces("application/xml")
	public Collection displayCollection(@PathParam("id") int CollectionId){
		Collection collection = collectionDAO.findById(CollectionId);
		return collection;
	}

	/*** Testé OK***/
	@GET
	@Path("/collections")
	@Produces({"application/xml","application/json"})
	public List<CollectionSimplifiee> getCollections(){
		List<CollectionSimplifiee> collection = collectionDAO.findAll();
		return collection;
	}
	
	@GET
	@Path("/collection/delete/{id}")
	public void deleteCollection(@PathParam("id")int id) {
		Collection collection = collectionDAO.findById(id);
		collectionDAO.deleteCollection(collection);			
	}

	/*** Testé OK***/
	@GET
	@Path("/oeuvres")
	@Produces("application/xml")
	public List<OeuvreSimplifiee> getAllOeuvres(){
		List<OeuvreSimplifiee> oeuvres = oeuvreDAO.findAll();
		return oeuvres;

	}

	/*** Testé OK***/
	@GET
	@Path("/oeuvre/{id}")
	@Produces("application/xml")
	public Oeuvre getOeuvre(@PathParam("id")int id){
		Oeuvre oeuvre = oeuvreDAO.findById(id);
		return oeuvre;
	}



	/*** Testé OK***/
	@POST
	@Path("/oeuvres/criteria")
	@Produces("application/xml")
	public OeuvresDTO getCriteriaOeuvre(Oeuvre oeuvre){
		OeuvresDTO  dto = oeuvreDAO.findByCriteria(oeuvre);
		return dto;
	}
	
	
	@GET
	@Path("/oeuvre/delete/{id}")
	public void deleteOeuvre(@PathParam("id")int id) {
		Oeuvre oeuvre = oeuvreDAO.findById(id);
		oeuvreDAO.removeOeuvre(oeuvre);			
	}
	
	@GET
	@Path("/artiste/delete/{id}")
	public void deleteArtiste(@PathParam("id")int id) {
		Artiste artiste = artisteDAO.findById(id);
		artisteDAO.deleteArtiste(artiste);			
	}


	/*** Testé OK***/
	@GET
	@Path("/artiste/{id}")
	@Produces("application/xml")
	public Artiste getArtiste(@PathParam("id")int id){
		Artiste a = artisteDAO.findById(id);
		return a;
	}


	@GET
	@Path("/oeuvre/artiste/{id}")
	@Produces({"application/xml","application/json"})
	public Set<OeuvreSimplifiee> findOeuvresOfArtiste(@PathParam("id")int ArtisteId)
	{
		Set<OeuvreSimplifiee> oeuvres = new HashSet<OeuvreSimplifiee>();
		oeuvres = artisteDAO.findOeuvresOfArtiste(ArtisteId);
		return oeuvres;	
	}

	@GET
	@Path("/artistes")
	@Produces({"application/xml","application/json"})
	public List<ArtisteSimplifie> getArtistes(){
		List<ArtisteSimplifie> a = artisteDAO.findAll();
		return a;
	}
}

