package com.ald.projet.property;

import javax.persistence.Embeddable;

@Embeddable
public enum Realisation {


	HUILE("Peinture a l'huile"),
	GOUACHE("Gouache") ,
	AQUARELLE("Aquarelle"),
	ACRYLIQUE("Acrylique");

	private final String stringValue;
	private Realisation(final String s) { stringValue = s; }
	public String toString() { return stringValue; }
}
