package com.ald.projet.simplified;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSeeAlso;

@XmlSeeAlso({PeintureSimplifiee.class, SculptureSimplifiee.class, PhotographieSimplifiee.class})
public class OeuvreSimplifiee {
	
	private int id;
	private String titre;
	private String type;


	public OeuvreSimplifiee(){

	}
	
	

	public OeuvreSimplifiee(int id, String titre, String type) {
		super();
		this.id = id;
		this.titre = titre;
		this.type = type;
	}



	@XmlElement
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@XmlElement
	public String getTitre() {
		return titre;
	}

	public void setTitre(String titre) {
		this.titre = titre;
	}


	@XmlElement
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	
	


}
