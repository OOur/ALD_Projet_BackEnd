package com.ald.projet.simplified;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "photographiesimplifiee")
public class PhotographieSimplifiee extends OeuvreSimplifiee {

	public PhotographieSimplifiee() {
		super();
	}

	public PhotographieSimplifiee(int id, String titre,	boolean hasBeenReproduced) {
		super(id, titre, "Photographie", hasBeenReproduced);

	}
	
}
