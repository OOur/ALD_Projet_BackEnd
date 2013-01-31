package com.ald.projet.service;

import java.util.List;
import java.util.Set;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;

import com.ald.projet.dto.OeuvresDTO;
import com.ald.projet.entities.Artiste;
import com.ald.projet.entities.Collection;
import com.ald.projet.entities.Employe;
import com.ald.projet.entities.Oeuvre;
import com.ald.projet.entities.Photo;
import com.ald.projet.entities.Reproduction;
import com.ald.projet.property.Connexion;

@Path("/service")
public interface MuseeService {

	@POST
	@Path("/connexion")
//	@Consumes({"application/xml","application/json"})
//	@Produces({"application/xml","application/json"})
	public Response connection(Connexion connexion);

	@POST
	@Path("/createCollection")
	@Consumes({"application/xml","application/json"})
	@Produces({"application/xml","application/json"})
	public Response createCollection(Collection collection);

	@POST
	@Path("/createOeuvre")
	@Consumes({"application/xml","application/json"})
	public Response createOeuvre(Oeuvre o);
		
	@POST
	@Path("/createReproduction")
	@Consumes({"application/xml","application/json"})
	public Response createReproduction(Reproduction r);
	
	@POST
	@Path("/createPhoto")
	@Consumes({"application/xml","application/json"})
	public Response createPhoto(Photo p);
	
	@POST
	@Path("/createArtiste")
	@Consumes({"application/xml","application/json"})
	public Response createArtiste(Artiste a);
	
	@POST
	@Path("/createEmploye")
	@Consumes({"application/xml","application/json"})
	public Response createEmploye(Employe e);
	
	@POST
	@Path("/updateOeuvre")
	@Consumes({"application/xml","application/json"})
	public Response updateOeuvre(Oeuvre o);

	@POST
	@Path("/updateCollection")
	@Consumes({"application/xml","application/json"})
	public Response updateCollection(Collection collection);

	@GET
	@Path("/contentOfCollection/{id}")
	@Produces({"application/xml","application/json"})
	public Collection displayCollection(@PathParam("id") int CollectionId);

	@GET
	@Path("/getCollections")
	@Produces({"application/xml","application/json"})
	public List<Collection> getCollections();

	@GET
	@Path("/oeuvres")
	@Produces({"application/xml","application/json"})
	public List<Oeuvre> getAllOeuvres();

	@GET
	@Path("/getOeuvre/{id}")
	@Produces({"application/xml","application/json"})
	public Oeuvre getOeuvre(@PathParam("id")int id);
	
	@POST
	@Path("/criteriaOeuvres")
	@Produces({"application/xml","application/json"})
	public OeuvresDTO getCriteriaOeuvre(Oeuvre oeuvre);
	
	@GET
	@Path("/deleteOeuvre/{id}")
	public void deleteOeuvre(@PathParam("id")int id);
	
	@GET
	@Path("/getOeuvresOfArtiste/{id}")
	@Produces({"application/xml","application/json"})
	public Set<Oeuvre> findOeuvresOfArtiste(@PathParam("id")int ArtisteId);
	
	@GET
	@Path("/getArtistes")
	@Produces({"application/xml","application/json"})
	public List<Artiste> getArtistes();
	
	@GET
	@Path("/getArtiste/{id}")
	@Produces({"application/xml","application/json"})
	public Artiste getArtiste(@PathParam("id")int id);
}


