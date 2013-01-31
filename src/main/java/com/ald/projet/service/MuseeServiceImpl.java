package com.ald.projet.service;

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
import com.ald.projet.dto.OeuvresDTO;
import com.ald.projet.entities.Artiste;
import com.ald.projet.entities.Collection;
import com.ald.projet.entities.Employe;
import com.ald.projet.entities.Oeuvre;
import com.ald.projet.entities.Photo;
import com.ald.projet.entities.Reproduction;
import com.ald.projet.property.Connexion;


public class MuseeServiceImpl implements MuseeService {

	private static final org.slf4j.Logger LOG = LoggerFactory.getLogger(MuseeServiceImpl.class);	
	private static CollectionDAO collectionDAO = new CollectionDAO();
	private static OeuvreDAO oeuvreDAO = new OeuvreDAO();
	private static ArtisteDAO artisteDAO = new ArtisteDAO();
	private static ReproductionDAO reproductionDAO = new ReproductionDAO();
	private static PhotoDAO photoDAO = new PhotoDAO();
	private static ConnexionDAO connexionDAO = new ConnexionDAO();


	public MuseeServiceImpl(){

	}

	/*** Test� OK***/
	public Response connection(Connexion connexion){

		LOG.info(Response.serverError().toString());

				LOG.info("login "+connexion.getLogin()+ " mdp "+connexion.getPassword());
				String status = connexionDAO.isValidConnection(connexion);
		/**
		 * erreur 400 : Could not find message body reader for type: class com.ald.projet.property.Connexion of content type: application/x-www-form-urlencoded. 
		 * D�s que je d�commente "Connexion connexion" pour l'interpr�ter depuis la requ�te HTTP re�ue �a plante. 
		 * Le contenu renvoy� par le client n'est pas au format xml mais une sorte de contenu de formulaire
		 * En attendant, je retourne toujours le status "Conservateur" pour pouvoir avancer. 
		 */
		String test = "Conservateur";
		return Response.ok(status).build();
	}


		/*** Test� OK***/
		public Response createCollection(Collection collection){
			collectionDAO.createCollection(collection);
			return Response.ok(collection).build();
		}
	
	
		/*** Test� OK***/
		public Response createOeuvre(Oeuvre o){
			oeuvreDAO.createOeuvre(o);
			return Response.ok(o).build();
		}
		
		/*** Test� OK***/
		public Response createReproduction(Reproduction r){
			reproductionDAO.createReproduction(r);
			return Response.ok(r).build();
		}
		
		/*** Test� OK***/
		public Response createPhoto(Photo p){
			photoDAO.createPhoto(p);
			return Response.ok(p).build();
		}
		
		/*** Test� OK***/
		public Response createArtiste(Artiste a){
			artisteDAO.createArtiste(a);
			return Response.ok(a).build();
		}
		
		/*** Test� OK***/
		public Response createEmploye(Employe e){
			connexionDAO.createEmploye(e);
			return Response.ok(e).build();
		}
		
	
		/*** Test� OK***/
		public Response updateOeuvre(Oeuvre o){
			oeuvreDAO.updateOeuvre(o);
			return Response.ok(o).build();
		}
	
		/*** Test� OK***/
		public Response updateCollection(Collection collection){
			collectionDAO.updateCollection(collection);
			return Response.ok(collection).build();
		}
	
		/*** Test� OK***/
		public Collection displayCollection(@PathParam("id") int CollectionId){
			Collection collection = collectionDAO.findById(CollectionId);
			return collection;
		}
	
		/*** Test� OK***/
		public List<Collection> getCollections(){
			List<Collection> collection = collectionDAO.findAll();
			return collection;
		}
	
		/*** Test� OK***/
		public List<Oeuvre> getAllOeuvres(){
			List<Oeuvre> oeuvres = oeuvreDAO.findAll();
			return oeuvres;
	
		}
	
		/*** Test� OK***/
		public Oeuvre getOeuvre(@PathParam("id")int id){
			Oeuvre oeuvre = oeuvreDAO.findById(id);
			return oeuvre;
		}
		
	
		/*** Test� OK***/
		public OeuvresDTO getCriteriaOeuvre(Oeuvre oeuvre){
			 OeuvresDTO  dto = oeuvreDAO.findByCriteria(oeuvre);
			return dto;
		}
		
		public void deleteOeuvre(int id) {
			Oeuvre oeuvre = oeuvreDAO.findById(id);
			oeuvreDAO.removeOeuvre(oeuvre);			
		}
		
	
		/*** Test� OK***/
		public List<Artiste> getArtistes(){
			List<Artiste> a = artisteDAO.findAll();
			return a;
		}
		
		/*** Test� OK***/
		public Artiste getArtiste(@PathParam("id")int id){
			Artiste a = artisteDAO.findById(id);
			return a;
		}

		public Set<Oeuvre> findOeuvresOfArtiste(int ArtisteId) {
			Set<Oeuvre> oeuvres = new HashSet<Oeuvre>();
			oeuvres = artisteDAO.findOeuvresOfArtiste(ArtisteId);
			return oeuvres;
		}

		

}

