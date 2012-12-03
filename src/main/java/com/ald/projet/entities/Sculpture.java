package com.ald.projet.entities;

import javax.persistence.Entity;


@Entity
public class Sculpture extends Oeuvre{

	private Materiaux materiaux;

	public Sculpture(){
		
	}
	

	public Sculpture(Materiaux materiaux) {
		super();
		this.materiaux = materiaux;
	}

	public Materiaux getMateriaux() {
		return materiaux;
	}

	public void setMateriaux(Materiaux materiaux) {
		this.materiaux = materiaux;
	}
	
	
}
