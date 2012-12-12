package com.ald.projet.entities;


import java.util.ArrayList;
import java.util.List;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.ald.projet.property.EtatCollection;

@Entity
@XmlRootElement(name = "collection")
public class Collection {
	
	@Id @GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	@Enumerated(EnumType.STRING)
	private EtatCollection etat;
	
	@ManyToMany
	private List<Oeuvre> oeuvres = new ArrayList<Oeuvre>();
	
	private String libele;
	private String commentaire;
	private String tag;
	
	
	
	public Collection(){
		
	}
	
	public Collection(List<Oeuvre> oeuvres) {
		super();
		this.oeuvres = oeuvres;
	}

	public void addOeuvre(Oeuvre oeuvre){
		oeuvres.add(oeuvre);
	}
	
	public void removeOeuvre(Oeuvre oeuvre){
		oeuvres.remove(oeuvre);
	}
	
	public void addComment(String comment){
		this.commentaire = comment;
	}
	
	public void addTag(String tag){
		this.tag = tag;
	}
	
	public String toString(){
		return null;
	}

	@XmlElement
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@XmlElement
	public EtatCollection getEtat() {
		return etat;
	}

	public void setEtat(EtatCollection etat) {
		this.etat = etat;
	}

	@XmlElement
	public List<Oeuvre> getOeuvres() {
		return oeuvres;
	}

	public void setOeuvres(List<Oeuvre> oeuvres) {
		this.oeuvres = oeuvres;
	}

	@XmlElement
	public String getLibele() {
		return libele;
	}

	public void setLibele(String libele) {
		this.libele = libele;
	}

	@XmlElement
	public String getCommentaire() {
		return commentaire;
	}

	public void setCommentaire(String commentaire) {
		this.commentaire = commentaire;
	}

	@XmlElement
	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}
	
	
	
	
	
}
