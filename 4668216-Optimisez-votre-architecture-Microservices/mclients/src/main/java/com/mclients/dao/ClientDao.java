package com.mclients.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mclients.model.Client;

@Repository
public interface ClientDao extends JpaRepository<Client,Integer>{

}
