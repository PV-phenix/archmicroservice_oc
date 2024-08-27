package com.uiclient.microservice.beans;

public class ExpeditionBean {
	
    public static final String PENDING  = "En attente";
    public static final String ACCEPTED = "Acceptée";
    public static final String REJECTED = "Refusée";
    public static final String FINISHED = "Terminée";
    public static final String SHIPPED  = "Expédié";
    

	private long Id;
    
	private long CommandeId;
	
	private String Etat;
	
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
