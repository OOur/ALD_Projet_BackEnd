package com.ald.projet.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;


@Entity
public class Conservateur extends AgentMusee{
	
	
	
	public Conservateur(){
		
	}
	
	public Conservateur(int id, String nom, String prenom, String login,
			String password) {
		super(id, nom, prenom, login, password);
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
	
	public void createCollection(){
		
	}
	
	public void addOeuvre(Oeuvre oeuvre, Collection collection){
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
