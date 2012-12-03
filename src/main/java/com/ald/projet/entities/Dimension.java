package com.ald.projet.entities;

import java.io.Serializable;

import javax.persistence.Embeddable;



@Embeddable
public class Dimension implements Serializable{
	int hauteur;
	int largeur;
	int longueur;
	
	
	public Dimension(){
		
	}
	
	public Dimension(int hauteur, int largeur, int longueur) {
		super();
		this.hauteur = hauteur;
		this.largeur = largeur;
		this.longueur = longueur;
	}

	public int getHauteur() {
		return hauteur;
	}

	public void setHauteur(int hauteur) {
		this.hauteur = hauteur;
	}

	public int getLargeur() {
		return largeur;
	}

	public void setLargeur(int largeur) {
		this.largeur = largeur;
	}

	public int getLongueur() {
		return longueur;
	}

	public void setLongueur(int longueur) {
		this.longueur = longueur;
	}
	
	
}
