package com.ald.projet.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

import com.ald.projet.entities.Artiste;
import com.ald.projet.entities.Photo;
import com.ald.projet.property.Dimension;

@XmlRootElement(name = "oeuvreDTO")
public class OeuvreDTO implements Serializable{

	
	private int id;


	@XmlElement(defaultValue = "false")
	private boolean hasBeenReproduced = false;




 
	private Integer annee;


	private String caracteristique;


	private String titre;

 
	private String resume;


	private String tag;

	public OeuvreDTO(){

	}

	public OeuvreDTO(Dimension dimension, boolean hasBeenReproduced,
			Artiste artiste, List<Photo> photo, Integer annee, String caracteristique,
			String titre, String resume, List<String> commentaire, String tag) {
		super();

		this.hasBeenReproduced = hasBeenReproduced;

	
		this.annee = annee;
		this.caracteristique = caracteristique;
		this.titre = titre;
		this.resume = resume;
		this.tag = tag;
	}


	@XmlElement
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}



	public boolean hasBeenReproduced() {
		return hasBeenReproduced;
	}

	public void setHasBeenReproduced(boolean hasBeenReproduced) {
		this.hasBeenReproduced = hasBeenReproduced;
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
	public String getTag() {
		return tag;
	}

	public void setTag(String tag) {
		this.tag = tag;
	}

}
