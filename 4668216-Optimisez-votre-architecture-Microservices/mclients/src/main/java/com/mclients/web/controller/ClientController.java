package com.mclients.web.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.mclients.dao.ClientDao;
import com.mclients.model.Client;



@RestController
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
	
    @GetMapping(value = "/clients")
    public ModelAndView  listeDesClients(Model model){
    	List<Client> clients = clientDao.findAll();
	    model.addAttribute("clients", clients);
	    ModelAndView modelAndView = new ModelAndView();
	   
	    modelAndView.setViewName("Accueil");	

	    return modelAndView;
    	
    }

}
