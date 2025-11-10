package com.mclients.service;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mclients.dao.ClientDao;
import com.mclients.model.Client;

import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;

@Service
public class ClientService {

    public EntityManager entityManager;
    
    @Autowired
    ClientDao clientDao;
    
    public ClientService(EntityManager entityManager) {
        this.entityManager = entityManager;
    }
 
    @Transactional
    public UUID saveClient(Client client){
        entityManager.persist(client);
        return client.getUUID();
    }

    @Transactional 
    public Iterable<Client> getClientsWithUuid() {
//   	Iterable<Client> lesClients =clientDao.findAll();
//    	for (int i = 0; i<clientDao.count();i++) {
//    		String uuid=lesClients.iterator().next().getUUID().toString();
//    		ListIterator<Client> clients;	
 //   		}
    	return clientDao.findAll();
    }
}
