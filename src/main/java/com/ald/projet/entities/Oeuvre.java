package com.ald.projet.entities;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

import com.ald.projet.property.Dimension;


@Entity @Inheritance(strategy=InheritanceType.SINGLE_TABLE) 
public abstract class Oeuvre {

	@Id @GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	@Embedded
	private Dimension dimension;
	//@Type(type="true_false")
	private boolean hasBeenReproduced;
	
//	@OneToMany
//	@JoinTable(name="artiste_oeuvre", joinColumns = {@JoinColumn(name="Oeuvre")}, inverseJoinColumns = {@JoinColumn(name="Artiste")})
//	private Artiste artiste;
//	
//	private int annee;
//	private String caracteristique;
//	private String titre;
//	private String resume;
//	private String commentaire;
//	private String tag;
	
	
	
	public Oeuvre(){
		
	}

	public Oeuvre(int id, Dimension dimension, Artiste artiste) {
		super();
		this.id = id;
		this.dimension = dimension;
		this.hasBeenReproduced = false;
		//this.artiste = artiste;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Dimension getDimension() {
		return dimension;
	}

	public void setDimension(Dimension dimension1) {
		this.dimension = dimension1;
	}
	
	public boolean hasBeenReproduced() {
		return hasBeenReproduced;
	}

	public void setHasBeenReproduced(boolean hasBeenReproduced) {
		this.hasBeenReproduced = hasBeenReproduced;
	}

//	public Artiste getArtiste() {
//		return artiste;
//	}
//
//	public void setArtiste(Artiste artiste) {
//		this.artiste = artiste;
//	}
//
//	public int getAnnee() {
//		return annee;
//	}
//
//	public void setAnnee(int annee) {
//		this.annee = annee;
//	}
//
//	public String getCaracteristique() {
//		return caracteristique;
//	}
//
//	public void setCaracteristique(String caracteristique) {
//		this.caracteristique = caracteristique;
//	}
//
//	public String getTitre() {
//		return titre;
//	}
//
//	public void setTitre(String titre) {
//		this.titre = titre;
//	}
//
//	public String getResume() {
//		return resume;
//	}
//
//	public void setResume(String resume) {
//		this.resume = resume;
//	}
//
//	public String getCommentaire() {
//		return commentaire;
//	}
//
//	public void setCommentaire(String commentaire) {
//		this.commentaire = commentaire;
//	}
//
//	public String getTag() {
//		return tag;
//	}
//
//	public void setTag(String tag) {
//		this.tag = tag;
//	}

		
	
	
	
}
