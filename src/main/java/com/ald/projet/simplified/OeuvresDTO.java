package com.ald.projet.simplified;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import com.ald.projet.entities.Oeuvre;

@XmlRootElement(name="oeuvresDTO")
public class OeuvresDTO {

	
	private List<Oeuvre> oeuvre = new ArrayList<Oeuvre>();

	
	public OeuvresDTO(){
		
	}
		
	public OeuvresDTO(List<Oeuvre> oeuvre) {
		super();
		this.oeuvre = oeuvre;
	}

	@XmlElement
	public List<Oeuvre> getOeuvre() {
		return oeuvre;
	}

	public void setOeuvre(List<Oeuvre> oeuvre) {
		this.oeuvre = oeuvre;
	}
}
