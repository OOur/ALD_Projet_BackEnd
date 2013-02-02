package com.ald.projet.simplified;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "peinturesimplifiee")
public class PeintureSimplifiee extends OeuvreSimplifiee{

	public PeintureSimplifiee(){
		
	}
	
	public PeintureSimplifiee(int id, String titre,	boolean hasBeenReproduced) {
		super(id, titre, "Peinture", hasBeenReproduced);
	}



		
	
}
