package com.ald.projet.entities;

import javax.persistence.CascadeType;
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
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.ald.projet.property.Dimension;



@Entity @Inheritance(strategy=InheritanceType.SINGLE_TABLE)
@XmlRootElement(name = "oeuvre")
public abstract class Oeuvre {

	@Id @GeneratedValue(strategy = GenerationType.AUTO) 
	private int id;
	@Embedded 
	private Dimension dimension;
	
	@XmlElement(defaultValue = "true")
	private boolean hasBeenReproduced;
	
	@OneToOne 
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

	
	
	
	public Oeuvre(Dimension dimension, boolean hasBeenReproduced,
			Artiste artiste, Integer annee, String caracteristique,
			String titre, String resume, String commentaire, String tag) {
		super();
		this.dimension = dimension;
		this.hasBeenReproduced = hasBeenReproduced;
		this.artiste = artiste;
		this.annee = annee;
		this.caracteristique = caracteristique;
		this.titre = titre;
		this.resume = resume;
		this.commentaire = commentaire;
		this.tag = tag;
	}


	@XmlElement
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@XmlElement
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

	@XmlElement
	public Artiste getArtiste() {
		return artiste;
	}

	public void setArtiste(Artiste artiste) {
		this.artiste = artiste;
	}

	@XmlElement
	public Integer getAnnee() {
		return annee;
	}

	public void setAnnee(Integer annee) {
		this.annee = annee;
	}

	@XmlElement
	public String getCaracteristique() {
		return caracteristique;
	}

	public void setCaracteristique(String caracteristique) {
		this.caracteristique = caracteristique;
	}

	@XmlElement
	public String getTitre() {
		return titre;
	}

	public void setTitre(String titre) {
		this.titre = titre;
	}

	@XmlElement
	public String getResume() {
		return resume;
	}

	public void setResume(String resume) {
		this.resume = resume;
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
