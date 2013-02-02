package com.ald.projet.simplified;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "sculpturesimplifiee")
public class SculptureSimplifiee extends OeuvreSimplifiee {

	public SculptureSimplifiee(){
		
	}
	
	
	public SculptureSimplifiee(int id, String titre, boolean hasBeenReproduced) {
		super(id, titre, "Sculpture", hasBeenReproduced);
	}

}
