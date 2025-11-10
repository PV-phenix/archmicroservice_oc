package com.mclients.dao;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.mclients.model.Client;

@Repository
public interface ClientDao extends JpaRepository<Client,UUID>{
	
	@Query("SELECT MAX(client.id) FROM Client client")
    UUID findClientByTopId();

}
