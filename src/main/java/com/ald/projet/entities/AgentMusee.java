package com.ald.projet.entities;

import javax.persistence.Entity;


@Entity
public abstract class AgentMusee extends Personne {

	private String login;
	private String password;
	
	
	public AgentMusee(){
		
	}
	
	
	public AgentMusee(int id, String nom, String prenom, String login, String password) {
		super(id, nom, prenom);
		this.login = login;
		this.password = password;
	}


	public String getLogin() {
		return login;
	}


	public void setLogin(String login) {
		this.login = login;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}
	
	

}
