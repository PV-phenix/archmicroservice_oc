package com.mclients.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.mclients.dao.ClientDao;
import com.mclients.model.Client;

import jakarta.persistence.EntityManager;

@Service
public class ClientService {

    public EntityManager entityManager;
    
    @Autowired
    ClientDao clientDao;
    
    public ClientService(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
 
    @Transactional(propagation = Propagation.REQUIRES_NEW, readOnly = false)
    public void saveClientbyIdMax(Client client){

		int idMax = clientDao.findClientByTopId();

		client.setId((idMax + 1));
    	clientDao.saveAndFlush(client);
        
    }

    @Transactional 
    public Iterable<Client> getClientsWithUuid() {
   	Iterable<Client> lesClients = clientDao.findAll();
   	
    	return lesClients;
    }
    
    @Transactional
	public void deleteClientById(int id) {
		clientDao.deleteById(id);
		clientDao.flush();
	}
    
}
