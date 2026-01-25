package com.uiclient.microservice.beans;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

public class ClientBean {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	private int id;

    private String nom;

    private String prenom;

    private String adresse;

    private String email;

 	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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
