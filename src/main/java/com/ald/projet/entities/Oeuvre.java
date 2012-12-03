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
	//@Type(type="yes_no")
	private boolean hasBeenReproduced;
	
	
	public Oeuvre(){
		
	}


	public Oeuvre(int id, Dimension dimension) {
		super();
		this.id = id;
		this.dimension = dimension;
		this.hasBeenReproduced = false;
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

	public void setDimension(Dimension dimension) {
		this.dimension = dimension;
	}
	
	public boolean hasBeenReproduced() {
		return hasBeenReproduced;
	}

	public void setHasBeenReproduced(boolean hasBeenReproduced) {
		this.hasBeenReproduced = hasBeenReproduced;
	}
	
	
	
}
