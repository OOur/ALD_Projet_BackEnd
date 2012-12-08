package com.ald.projet.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.ald.projet.DAO.ArtisteDAO;
import com.ald.projet.DAO.CollectionDAO;


@Entity
public class Conservateur extends AgentMusee{
	
	private static CollectionDAO collectionDAO = new CollectionDAO();
	
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
		
	}
	
//	public void addInfoOeuvre(Oeuvre oeuvre, String info){
//	}
	
	public void addAbstractOeuvre(Oeuvre oeuvre, String abstrac){
	}
	
	public void addCommentOeuvre(Oeuvre oeuvre, String comment){
	}
	
	public void addTagOeuvre(Oeuvre oeuvre, String tag){
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
	}
	
	public void addCommentCollection(Oeuvre oeuvre, String comment){
	}
	
	public void displayCollection(Collection collection){
	}
	
	public void addTagCollection(Collection collection, String tag){
	}
}
