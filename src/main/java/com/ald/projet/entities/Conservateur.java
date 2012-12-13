package com.ald.projet.entities;

import java.util.List;

import javax.persistence.Entity;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import org.slf4j.LoggerFactory;

import com.ald.projet.DAO.ArtisteDAO;
import com.ald.projet.DAO.CollectionDAO;
import com.ald.projet.DAO.OeuvreDAO;
import com.ald.projet.property.Dimension;
import com.ald.projet.property.Realisation;
import com.ald.projet.property.SupportOeuvre;

/**
 * 
 * La persitence des donnees se fait directement dans cette classe grace au DAO.
 * 
 */


@Entity
@Path("/conservateur")
public class Conservateur extends AgentMusee{

	private static final org.slf4j.Logger LOG = LoggerFactory.getLogger(Conservateur.class);	
	private static CollectionDAO collectionDAO = new CollectionDAO();
	private static OeuvreDAO oeuvreDAO = new OeuvreDAO();
	private static ArtisteDAO artisteDAO = new ArtisteDAO();


	public Conservateur(){

	}

	public Conservateur(String nom, String prenom, String login,
			String password) {
		super(nom, prenom, login, password);
	}


	public Oeuvre takePhoto(Oeuvre oeuvre){
		return null;
	}

	public void addCaracteristiqueOeuvre(Oeuvre oeuvre, String carac){
		oeuvre.setCaracteristique(carac);
		oeuvreDAO.updateOeuvre(oeuvre);

	}

	public void addAbstractOeuvre(Oeuvre oeuvre, String abstrac){
		oeuvre.setCaracteristique(abstrac);
		oeuvreDAO.updateOeuvre(oeuvre);
	}

	public void addCommentOeuvre(Oeuvre oeuvre, String comment){
		oeuvre.setCaracteristique(comment);
		oeuvreDAO.updateOeuvre(oeuvre);
	}

	public void addTagOeuvre(Oeuvre oeuvre, String tag){
		oeuvre.setCaracteristique(tag);
		oeuvreDAO.updateOeuvre(oeuvre);
	}

	@GET
	@Path("/oeuvres")
	@Produces("application/xml")
	public List<Oeuvre> getAllOeuvres(){
		List<Oeuvre> oeuvres = oeuvreDAO.findAll();
		return oeuvres;

	}


	public Collection createCollection(){
		Collection collection = new Collection();
		collectionDAO.createCollection(collection);
		return collection;

	}

	public void addOeuvre(Oeuvre oeuvre, Collection collection){
		collection.addOeuvre(oeuvre);
		collectionDAO.updateCollection(collection);
	}

	public void removeOeuvre(Oeuvre oeuvre, Collection collection){
		collection.removeOeuvre(oeuvre);
		collectionDAO.updateCollection(collection);
	}

	@POST
	@Path("/comment")
	@Consumes("application/xml")
	public void addCommentCollection(Collection collection, String comment){
		collection.addComment(comment);
		collectionDAO.updateCollection(collection);
	}

	@GET
	@Path("/contentOfCollection/{id}")
	@Produces("application/xml")
	public List<Oeuvre> displayCollection(@PathParam("id") int CollectionId){
		List<Oeuvre> oeuvres = collectionDAO.getOeuvresOfCollection(CollectionId);
		return oeuvres;
	}

	@GET
	@Path("/getCollections")
	@Produces("application/xml")
	public List<Collection> getCollections(){
		List<Collection> collection = collectionDAO.findAll();
		return collection;
	}


	@GET
	@Path("/getOeuvre/{id}")
	@Produces("application/xml")
	public Oeuvre getCollection(@PathParam("id")int id){
		Oeuvre oeuvre = oeuvreDAO.findById(id);
		return oeuvre;


	}

	public void addTagCollection(Collection collection, String tag){
		collection.addTag(tag);
		collectionDAO.updateCollection(collection);
	}
}
