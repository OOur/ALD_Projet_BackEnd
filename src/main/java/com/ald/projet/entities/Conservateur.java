package com.ald.projet.entities;

import java.util.List;

import javax.persistence.Entity;

import com.ald.projet.DAO.CollectionDAO;
import com.ald.projet.DAO.OeuvreDAO;

/**
 * 
 * La persitence des données se fait directement dans cette classe grâce au DAO.
 * 
 */


@Entity
public class Conservateur extends AgentMusee{
	
	private static CollectionDAO collectionDAO = new CollectionDAO();
	private static OeuvreDAO oeuvreDAO = new OeuvreDAO();
	
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
	
	public void addCommentCollection(Collection collection, String comment){
		collection.addComment(comment);
		collectionDAO.updateCollection(collection);
	}
	
	
	/* NE MARCHE PAS, A CORRIGER */
	public List<Oeuvre> displayCollection(Collection collection){
		
		
		
	List<Oeuvre> oeuvres = collectionDAO.findAllOeuvreOfCollection(collection);
//		String affiche ="";
//		int i = 0;
//		
//		for(Oeuvre o : oeuvres){
//			affiche += "Oeuvre "+i+" de type "+ o.getClass().getName();
//		}
//		
//		return affiche;
	
	return oeuvres;
	}
	
	public void addTagCollection(Collection collection, String tag){
		collection.addTag(tag);
		collectionDAO.updateCollection(collection);
	}
}
