package com.mcommerce.mexpedition.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;


@Entity
public class Expedition {

    public static final String PENDING  = "En attente";
    public static final String ACCEPTED = "Acceptée";
    public static final String REJECTED = "Refusée";
    public static final String FINISHED = "Terminée";
    public static final String SHIPPED  = "Expédié";
    
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private long Id;
    
	private long CommandeId;
	
	private String Etat;
	
    
    public Expedition() {}
    
	public Expedition(long id, long commandeId, String etat) {
		super();
		Id = id;
		CommandeId = commandeId;
		Etat = etat;
	}

	public long getId() {
		return Id;
	}
	public void setId(long id) {
		Id = id;
	}
	public long getCommandeId() {
		return CommandeId;
	}
	public void setCommandeId(long commandeId) {
		CommandeId = commandeId;
	}
	public String getEtat() {
		return Etat;
	}
	public void setEtat(String etat) {
		Etat = etat;
	}

}
