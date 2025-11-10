package com.uiclient.microservice.beans;

import java.util.UUID;

public class ClientBean {

    private UUID id;

    private String nom;

    private String prenom;

    private String adresse;

    private String email;

 	public UUID getUUID() {
		return id;
	}

	public void setUUID(UUID i) {
		this.id = i;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getAdresse() {
		return adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
    @Override
    public String toString() {
        return "ClientBean{" +
                "id=" + id +
                ",  nom=" +  nom +
                ", prenom=" + prenom +
                ", adresse=" + adresse +
                ", email" + email+
                "}";
    }

}
