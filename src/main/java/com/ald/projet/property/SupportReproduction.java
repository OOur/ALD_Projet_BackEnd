package com.ald.projet.property;

import javax.persistence.Embeddable;

@Embeddable
public enum SupportReproduction {

	CARTE("Carte postale"),
	TOILE("Toile de lin") ,
	AFFICHE ("Affiche");	

	private final String stringValue;
	private SupportReproduction(final String s) { stringValue = s; }
	public String toString() { return stringValue; }

}
