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
import com.ald.projet.dto.OeuvreDTO;
import com.ald.projet.dto.OeuvresDTO;
import com.ald.projet.entities.Artiste;
import com.ald.projet.entities.Collection;
import com.ald.projet.entities.Employe;
import com.ald.projet.entities.Oeuvre;
import com.ald.projet.entities.Photo;
import com.ald.projet.entities.Reproduction;
import com.ald.projet.property.Connexion;

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



	/*** Test� OK***/
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
		 * D�s que je d�commente "Connexion connexion" pour l'interpr�ter depuis la requ�te HTTP re�ue �a plante. 
		 * Le contenu renvoy� par le client n'est pas au format XML mais une sorte de contenu de formulaire
		 * En attendant, je retourne toujours le status "Conservateur" pour pouvoir avancer. 
		 */
		String test = "Conservateur";
		return Response.ok(status).build();

	}


	/*** Test� OK***/
	@POST
	@Path("/createCollection")
	@Consumes("application/xml")
	@Produces("application/xml")
	public Response createCollection(Collection collection){
		collectionDAO.createCollection(collection);
		return Response.ok(collection).build();
	}


	/*** Test� OK***/
	@POST
	@Path("/createOeuvre")
	@Consumes("application/xml")
	public Response createOeuvre(Oeuvre o){
		oeuvreDAO.createOeuvre(o);
		return Response.ok(o).build();
	}

	/*** Test� OK***/
	@POST
	@Path("/createReproduction")
	@Consumes("application/xml")
	public Response createReproduction(Reproduction r){
		reproductionDAO.createReproduction(r);
		return Response.ok(r).build();
	}

	/*** Test� OK***/
	@POST
	@Path("/createPhoto")
	@Consumes("application/xml")
	public Response createPhoto(Photo p){
		photoDAO.createPhoto(p);
		return Response.ok(p).build();
	}

	/*** Test� OK***/
	@POST
	@Path("/createArtiste")
	@Consumes("application/xml")
	public Response createArtiste(Artiste a){
		artisteDAO.createArtiste(a);
		return Response.ok(a).build();
	}

	/*** Test� OK***/
	@POST
	@Path("/createEmploye")
	@Consumes("application/xml")
	public Response createEmploye(Employe e){
		connexionDAO.createEmploye(e);
		return Response.ok(e).build();
	}


	/*** Test� OK***/
	@POST
	@Path("/updateOeuvre")
	@Consumes("application/xml")
	public Response updateOeuvre(Oeuvre o){
		oeuvreDAO.updateOeuvre(o);
		return Response.ok(o).build();
	}

	/*** Test� OK***/
	@POST
	@Path("/updateCollection")
	@Consumes("application/xml")
	public Response updateCollection(Collection collection){
		collectionDAO.updateCollection(collection);
		return Response.ok(collection).build();
	}

	/*** Test� OK***/
	@GET
	@Path("/contentOfCollection/{id}")
	@Produces("application/xml")
	public Collection displayCollection(@PathParam("id") int CollectionId){
		Collection collection = collectionDAO.findById(CollectionId);
		return collection;
	}

	/*** Test� OK***/
	@GET
	@Path("/getCollections")
	@Produces("application/xml")
	public List<Collection> getCollections(){
		List<Collection> collection = collectionDAO.findAll();
		return collection;
	}

	/*** Test� OK***/
	@GET
	@Path("/oeuvres")
	@Produces("application/xml")
	public List<Oeuvre> getAllOeuvres(){
		List<Oeuvre> oeuvres = oeuvreDAO.findAll();
		return oeuvres;

	}

	/*** Test� OK***/
	@GET
	@Path("/getOeuvre/{id}")
	@Produces("application/xml")
	public Oeuvre getOeuvre(@PathParam("id")int id){
		Oeuvre oeuvre = oeuvreDAO.findById(id);
		return oeuvre;
	}



	/*** Test� OK***/
	@POST
	@Path("/criteriaOeuvres")
	@Produces("application/xml")
	public OeuvresDTO getCriteriaOeuvre(Oeuvre oeuvre){
		OeuvresDTO  dto = oeuvreDAO.findByCriteria(oeuvre);
		return dto;
	}


	/*** Test� OK***/
	@GET
	@Path("/getArtiste/{id}")
	@Produces("application/xml")
	public Artiste getArtiste(@PathParam("id")int id){
		Artiste a = artisteDAO.findById(id);
		return a;
	}


	@GET
	@Path("/getOeuvresOfArtiste/{id}")
	@Produces({"application/xml","application/json"})
	public Set<Oeuvre> findOeuvresOfArtiste(@PathParam("id")int ArtisteId)
	{
		Set<Oeuvre> oeuvres = new HashSet<Oeuvre>();
		oeuvres = artisteDAO.findOeuvresOfArtiste(ArtisteId);
		return oeuvres;	
	}

	@GET
	@Path("/getArtistes")
	@Produces({"application/xml","application/json"})
	public List<Artiste> getArtistes(){
		List<Artiste> a = artisteDAO.findAll();
		return a;
	}
}

