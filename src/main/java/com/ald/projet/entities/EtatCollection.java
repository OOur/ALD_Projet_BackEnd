package com.ald.projet.entities;

import javax.persistence.Embeddable;

@Embeddable
public class EtatCollection {
	private boolean isExposed;
	private boolean isInReserve;
	private boolean isInRestoration;
	
	
}
