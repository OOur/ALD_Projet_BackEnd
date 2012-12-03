package com.ald.projet.entities;

public enum Support {


	LIN("Toile de lin"), COTON("Toile de coton"), PAPIER("Papier") ,
	CARTON("carton"),
	BOIS("bois"),
	ALUMINIUM("aluminium") ;	
	
	private final String stringValue;
	private Support(final String s) { stringValue = s; }
	public String toString() { return stringValue; }

}
