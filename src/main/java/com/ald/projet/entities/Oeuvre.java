package com.ald.projet.entities;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

import com.ald.projet.property.Dimension;


@Entity @Inheritance(strategy=InheritanceType.SINGLE_TABLE) 
public abstract class Oeuvre {

	@Id @GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	@Embedded
	private Dimension dimension;
	private boolean hasBeenReproduced;
	
	@OneToOne /*(cascade=CascadeType.PERSIST)*/
	@JoinColumn(name="artiste_id", nullable=false)
	private Artiste artiste;
	
	@Column(nullable=true)
	private Integer annee;
	@Column(nullable=true)
	private String caracteristique;
	@Column(nullable=true)
	private String titre;
	@Column(nullable=true)
	private String resume;
	@Column(nullable=true)
	private String commentaire;
	@Column(nullable=true)
	private String tag;
	
	
	
	public Oeuvre(){
		
	}

	public Oeuvre(int id, Dimension dimension, Artiste artiste) {
		super();
		this.id = id;
		this.dimension = dimension;
		this.hasBeenReproduced = false;
		this.artiste = artiste;
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

	public Artiste getArtiste() {
		return artiste;
	}

	public void setArtiste(Artiste artiste) {
		this.artiste = artiste;
	}

	public Integer getAnnee() {
		return annee;
	}

	public void setAnnee(Integer annee) {
		this.annee = annee;
	}

	public String getCaracteristique() {
		return caracteristique;
	}

	public void setCaracteristique(String caracteristique) {
		this.caracteristique = caracteristique;
	}

	public String getTitre() {
		return titre;
	}

	public void setTitre(String titre) {
		this.titre = titre;
	}

	public String getResume() {
		return resume;
	}

	public void setResume(String resume) {
		this.resume = resume;
	}

	public String getCommentaire() {
		return commentaire;
	}

	public void setCommentaire(String commentaire) {
		this.commentaire = commentaire;
	}

	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

		
	
	
	
}
