package com.ald.projet.entities;

public enum Realisation {


	HUILE("Peinture a l'huile"),
	GOUACHE("Gouache") ,
	AQUARELLE("Aquarelle"),
	ACRYLIQUE("Acrylique");

	private final String stringValue;
	private Realisation(final String s) { stringValue = s; }
	public String toString() { return stringValue; }
}
