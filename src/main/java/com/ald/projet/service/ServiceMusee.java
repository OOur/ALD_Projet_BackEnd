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

@Path("/service")
public interface ServiceMusee {


	@POST
	@Path("/connexion")
	@Consumes("application/xml")
	@Produces("application/xml")
	public Response connection(Connexion connexion);


	@POST
	@Path("/collection/create")
	@Consumes("application/xml")
	@Produces("application/xml")
	public Response createCollection(Collection collection);

	
	@POST
	@Path("/oeuvre/create")
	@Consumes("application/xml")
	public Response createOeuvre(Oeuvre o);

	
	@POST
	@Path("/reproduction/create")
	@Consumes("application/xml")
	public Response createReproduction(Reproduction r);

	
	@POST
	@Path("/photo/create")
	@Consumes("application/xml")
	public Response createPhoto(Photo p);

	
	@POST
	@Path("/artiste/create")
	@Consumes("application/xml")
	public Response createArtiste(Artiste a);

	
	@POST
	@Path("/employe/create")
	@Consumes("application/xml")
	public Response createEmploye(Employe e);


	@POST
	@Path("/oeuvre/update")
	@Consumes("application/xml")
	public Response updateOeuvre(Oeuvre o);

	
	@POST
	@Path("/collection/update")
	@Consumes("application/xml")
	public Response updateCollection(Collection collection);

	
	@GET
	@Path("/collection/{id}")
	@Produces("application/xml")
	public Collection displayCollection(@PathParam("id") int CollectionId);

	
	@GET
	@Path("/collections")
	@Produces({"application/xml","application/json"})
	public List<CollectionSimplifiee> getCollections();

	
	@GET
	@Path("/collection/delete/{id}")
	public void deleteCollection(@PathParam("id")int id);

	
	@GET
	@Path("/oeuvres")
	@Produces("application/xml")
	public List<OeuvreSimplifiee> getAllOeuvres();

	
	@GET
	@Path("/oeuvre/{id}")
	@Produces("application/xml")
	public Oeuvre getOeuvre(@PathParam("id")int id);



	@POST
	@Path("/oeuvres/criteria")
	@Produces("application/xml")
	public List<Oeuvre> getCriteriaOeuvre(Oeuvre oeuvre);

	
	@GET
	@Path("/oeuvres/notReproduced")
	@Produces("application/xml")
	public List<OeuvreSimplifiee> getAllOeuvreNotReproduced();
	
	
	@GET
	@Path("/oeuvre/{id}/reproductions")
	@Produces("application/xml")
	public List<Reproduction> getReproductionsOfOeuvre(@PathParam("id")int id);
	
	
	@GET
	@Path("/oeuvre/{id}/nbReproductions")
	public long getNbReproductionOfOeuvre(@PathParam("id")int id);

	
	@GET
	@Path("/reproduction/{id}/{price}")
	public double setReproductionPrice(@PathParam("id")int id, @PathParam("price")double price); 
	
	
	@GET
	@Path("/reproductions/collection/{id}")
	@Produces("application/xml")
	public List<Reproduction> getReproductionsOfCollection(@PathParam("id")int id);
	
		
	@GET
	@Path("/oeuvre/delete/{id}")
	public void deleteOeuvre(@PathParam("id")int id);

	
	@GET
	@Path("/artiste/delete/{id}")
	public void deleteArtiste(@PathParam("id")int id);


	@GET
	@Path("/artiste/{id}")
	@Produces("application/xml")
	public Artiste getArtiste(@PathParam("id")int id);


	@GET
	@Path("/oeuvre/artiste/{id}")
	@Produces({"application/xml","application/json"})
	public Set<OeuvreSimplifiee> findOeuvresOfArtiste(@PathParam("id")int ArtisteId);

	
	@GET
	@Path("/artistes")
	@Produces({"application/xml","application/json"})
	public List<ArtisteSimplifie> getArtistes();
}
