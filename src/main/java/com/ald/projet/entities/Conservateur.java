package com.ald.projet.entities;

import java.util.List;

import javax.persistence.Entity;
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
import com.ald.projet.DAO.OeuvreDAO;

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


	@GET
	@Path("/oeuvres")
	@Produces("application/xml")
	public List<Oeuvre> getAllOeuvres(){
		List<Oeuvre> oeuvres = oeuvreDAO.findAll();
		return oeuvres;

	}

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

	/*** Ne marche pas, cree une autre oeuvre plutôt que de mettre a jour **/
	@POST
	@Path("/updateOeuvre")
	@Consumes("application/xml")
	public Response updateOeuvre(Oeuvre o){
		oeuvreDAO.updateOeuvre(o);
		return Response.ok(o).build();
	}

	@POST
	@Path("/updateCollection")
	@Consumes("application/xml")
	public Response updateCollection(Collection collection){
		collectionDAO.updateCollection(collection);
		return Response.ok(collection).build();
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

	@GET
	@Path("/getArtiste/{id}")
	@Produces("application/xml")
	public Artiste getArtiste(@PathParam("id")int id){
		Artiste a = artisteDAO.findById(id);
		return a;
	}
}

