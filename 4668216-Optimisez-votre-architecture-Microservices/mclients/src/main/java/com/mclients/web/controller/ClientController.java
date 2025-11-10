package com.mclients.web.controller;

import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
//import org.hibernate.jpa.HibernatePersistenceProvider;

import com.mclients.dao.ClientDao;
import com.mclients.model.Client;

import io.swagger.v3.oas.annotations.tags.Tag;
//import jakarta.persistence.EntityManager;
//import jakarta.persistence.EntityManagerFactory;
//import jakarta.persistence.Persistence;



@RestController
@Tag(name = "Gestion Clients", description = "Operations relatives aux clients")
public class ClientController  implements HealthIndicator {
	
	   public ClientController(ClientDao clientDao){
	    	this.clientDao = clientDao;
	    	
	       	}
	
    @Autowired
    ClientDao clientDao;
      
    Logger log = LoggerFactory.getLogger(this.getClass());

	

	@Override
	public Health health() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Tag(name = "Les clients - Affichage")
    @GetMapping("/lesclients")
    public Iterable<Client>  lesClients()
	{
		Iterable<Client> clients = clientDao.findAll();
		return clients;
	}
	
	@Tag(name = "Les clients Admin- Affichage")
    @GetMapping("/clients")
    public ModelAndView  listeDesClients(Model model){
		
		
    	Iterable<Client> clients = clientDao.findAll();
//    	
//    	List<String> uuids =new ArrayList<String>();
//    	
//    	for (int i = 0; i<clientDao.count();i++) {
//    	
//    	  	String uuid=clients.iterator().next().getUUID().toString();
//    		uuids.add(uuid);
//    	    		
//    	}
//    	
//    	model.addAttribute("uuids",uuids);
    	
	    model.addAttribute("clients", clients);

	    ModelAndView modelAndView = new ModelAndView();
	   
	    modelAndView.setViewName("Accueil");	

	    return modelAndView;
    	
    }
	
	
//	public Optional<Client> findClient() {
//		Optional<Client> client = clientDao.findById(findClientTopId());
//		if(client.isEmpty()) throw new ClientNotFoundException("Cette commande n'existe pas");
//		
//		return client;
//		
//	}

	
	@Tag(name = "Les clients - Ajout")
    @GetMapping("/clients/ajout")
	public ModelAndView  creationClient(Model model)
	{
		Client client = new Client();
		
		client.setUUID(UUID.randomUUID());
		
		
		model.addAttribute("uuid",client.getUUID().toString());
		model.addAttribute("client", client);

	    ModelAndView modelAndView = new ModelAndView();
	   
	    modelAndView.setViewName("AddClient");	

	    return modelAndView;
	}


	@Tag(name = "Les clients - Sauvegarde d'un client")
    @PostMapping(value = "/clients/sauveclient")
   	public void  sauveClient(Client client){

		clientDao.saveAndFlush(client);
//		}
	}
	
	

}
