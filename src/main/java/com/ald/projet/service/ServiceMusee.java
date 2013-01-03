package com.ald.projet.service;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.ws.rs.Consumes;
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
import com.ald.projet.entities.Artiste;
import com.ald.projet.entities.Collection;
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

	
	
	/*** Testé OK***/
	@POST
	@Path("/connexion")
	@Consumes("application/xml")
	public Response connection(Connexion connexion){	
		String status = connexionDAO.isValidConnection(connexion);
		return Response.ok(status).build();
	}
	

	/*** Testé OK***/
	@POST
	@Path("/createCollection")
	@Consumes("application/xml")
	public Response createCollection(Collection collection){
		collectionDAO.createCollection(collection);
		return Response.ok(collection).build();
	}


	/*** Testé OK***/
	@POST
	@Path("/createOeuvre")
	@Consumes("application/xml")
	public Response createOeuvre(Oeuvre o){
		oeuvreDAO.createOeuvre(o);
		return Response.ok(o).build();
	}
	
	/*** Testé OK***/
	@POST
	@Path("/createReproduction")
	@Consumes("application/xml")
	public Response createReproduction(Reproduction r){
		reproductionDAO.createReproduction(r);
		return Response.ok(r).build();
	}
	
	/*** Testé OK***/
	@POST
	@Path("/createPhoto")
	@Consumes("application/xml")
	public Response createPhoto(Photo p){
		photoDAO.createPhoto(p);
		return Response.ok(p).build();
	}
	

	/*** Testé OK***/
	@POST
	@Path("/updateOeuvre")
	@Consumes("application/xml")
	public Response updateOeuvre(Oeuvre o){
		oeuvreDAO.updateOeuvre(o);
		return Response.ok(o).build();
	}

	/*** Testé OK***/
	@POST
	@Path("/updateCollection")
	@Consumes("application/xml")
	public Response updateCollection(Collection collection){
		collectionDAO.updateCollection(collection);
		return Response.ok(collection).build();
	}

	/*** Testé OK***/
	@GET
	@Path("/contentOfCollection/{id}")
	@Produces("application/xml")
	public Collection displayCollection(@PathParam("id") int CollectionId){
		Collection collection = collectionDAO.findById(CollectionId);
		return collection;
	}

	/*** Testé OK***/
	@GET
	@Path("/getCollections")
	@Produces("application/xml")
	public List<Collection> getCollections(){
		List<Collection> collection = collectionDAO.findAll();
		return collection;
	}

	/*** Testé OK***/
	@GET
	@Path("/oeuvres")
	@Produces("application/xml")
	public List<Oeuvre> getAllOeuvres(){
		List<Oeuvre> oeuvres = oeuvreDAO.findAll();
		return oeuvres;

	}

	/*** Testé OK***/
	@GET
	@Path("/getOeuvre/{id}")
	@Produces("application/xml")
	public Oeuvre getOeuvre(@PathParam("id")int id){
		Oeuvre oeuvre = oeuvreDAO.findById(id);
		return oeuvre;
	}

	/*** Testé OK***/
	@GET
	@Path("/getArtiste/{id}")
	@Produces("application/xml")
	public Artiste getArtiste(@PathParam("id")int id){
		Artiste a = artisteDAO.findById(id);
		return a;
	}
}

