package com.ald.projet.entities;

import javax.persistence.Embedded;
import javax.persistence.Entity;

import com.ald.projet.property.Materiaux;


@Entity
public class Sculpture extends Oeuvre{

	@Embedded
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
