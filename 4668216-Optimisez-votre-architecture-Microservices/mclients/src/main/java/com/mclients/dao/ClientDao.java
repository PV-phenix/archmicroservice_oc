package com.mclients.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.mclients.model.Client;

import jakarta.transaction.Transactional;

@Repository
public interface ClientDao extends JpaRepository<Client,Integer>{
	

	@Query("SELECT MAX(client.id) FROM Client client")
    int findClientByTopId();
	
	@Transactional
	@Modifying
	@Query("INSERT INTO Client client(id, nom, prenom,adresse,email) VALUES (?1,?2,?3,?4,?5)")
	  void insertClient(int id, String  nom, String prenom, String adresse, String email);

}

