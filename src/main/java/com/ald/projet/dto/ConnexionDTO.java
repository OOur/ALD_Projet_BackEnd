package com.ald.projet.dto;

import javax.persistence.Embeddable;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "connexion")
public class ConnexionDTO {

	private String login;
	private String password;
	
	public ConnexionDTO(){
		
	}
	
	public ConnexionDTO(String login, String password) {
		super();
		this.login = login;
		this.password = password;
	}
	
	@XmlElement
	public String getLogin() {
		return login;
	}
	public void setLogin(String login) {
		this.login = login;
	}
	
	@XmlElement
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
}

