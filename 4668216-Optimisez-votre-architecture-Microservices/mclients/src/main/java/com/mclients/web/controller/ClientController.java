package com.mclients.web.controller;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.health.contributor.Health;
import org.springframework.boot.health.contributor.HealthIndicator;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import com.mclients.dao.ClientDao;
import com.mclients.dto.UserDto;
import com.mclients.model.Client;
import com.mclients.service.ClientService;

import io.swagger.v3.oas.annotations.tags.Tag;



@RestController
@Tag(name = "Gestion Clients", description = "Operations relatives aux clients")
public class ClientController  implements HealthIndicator {
	
	   public ClientController(ClientDao clientDao){
	    	this.clientDao = clientDao;
	    	
	       	}
	
    @Autowired
    ClientDao clientDao;
    
    @Autowired
    ClientService clientService;
      
    Logger log = LoggerFactory.getLogger(this.getClass());

	

	@Override
	public Health health() {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Tag(name = "Accueil Clients")
    @GetMapping("/")
    public ModelAndView pageAccueil()
	{
	   	ModelAndView modelAndView = new ModelAndView();
		   
    	modelAndView.setViewName("Accueil");	

	    return modelAndView;
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
    	
    	model.addAttribute("clients", clients);

    	ModelAndView modelAndView = new ModelAndView();
	   
    	modelAndView.setViewName("Clients");	

	    return modelAndView;
    	
    }
	  @PostMapping(value="/clients/maj/{id}",consumes="application/json")//consumes = {"*/*"}
	  public  RedirectView upClient(@RequestParam int id,@RequestBody Client client)
	  {

		  clientDao.saveAndFlush(client);

		  RedirectView redirectView = new RedirectView();
		  redirectView.setUrl("/");
		 
		  return redirectView;

	  }
	

	
	@Tag(name = "Les clients - Ajout")
    @GetMapping("/clients/ajout")
	public ModelAndView  creationClient(Model model)
	{
		Client client = new Client();
		int idMax = clientDao.findClientByTopId();
		client.setId(idMax + 1);
		
		model.addAttribute("client", client);

	    ModelAndView modelAndView = new ModelAndView();
	   
	    modelAndView.setViewName("AddClient");	

	    return modelAndView;
	}


	@Tag(name = "Les clients - Sauvegarde d'un client")
    @PostMapping(value = "/clients/sauveclient")
   	public RedirectView  sauveClient(Client client){
		int maxId =clientDao.findClientByTopId();
		client.setId(maxId + 1);
		
		clientDao.insertClient(client.getId(), client.getNom(), client.getPrenom(),client.getAdresse(), client.getEmail());
		
		RedirectView redirectView = new RedirectView();
		redirectView.setUrl("/clients");

		return redirectView;
   	
	}
	
	@Tag(name = "Les clients - Suppression d'un client")
	@PostMapping(value = "/clients/delete/{id}")
	public RedirectView DeleteClientById(@PathVariable int id) {
		
		clientService.deleteClientById(id);
		RedirectView redirectView = new RedirectView();
        redirectView.setUrl("/clients");
		return redirectView; 
		}

	
	@Tag(name = "Les clients - Suppression d'un client")
	@DeleteMapping(value = "/lesclients/del/{id}")
	public void deleteLesClientById(@PathVariable int id) {
		
		clientService.deleteClientById(id);


		}
	
	@Tag(name = "Les clients - Mise à jour d'un client")
	@PostMapping("/lesclients/update/{id}")
	public ModelAndView miseAjourLesClientById(@PathVariable int id,Model model) {
		

		Optional<Client> client =clientDao.findById(id);
		model.addAttribute("client", client);

	    ModelAndView modelAndView = new ModelAndView();
	   
	    modelAndView.setViewName("MajClient");	

	    return modelAndView;
		}
	
	@Tag(name = "Les clients - Mise à jour d'un client")
	@PostMapping(value="/clients/update/{id}")
	public ModelAndView miseAjourClientById(@PathVariable int id,Model model) {
		

		Optional<Client> client =clientDao.findById(id);
		
		//if (client !=null)
		model.addAttribute("client", client);

	    ModelAndView modelAndView = new ModelAndView();
	   
	    modelAndView.setViewName("UpdateClient");	

	    return modelAndView;
		}
	
	@Tag(name = "Les clients - Client mis à jour")
    @PostMapping(value = "/lesclients/maj/{id}",consumes = {"*/*"})//consumes = {"*/*"})
   	public void  majClient(@RequestParam int id,@RequestBody Client client){
		
		clientDao.saveAndFlush(client);
		
//		RedirectView redirectView = new RedirectView();
//		redirectView.setUrl("/clients");
//
//		return redirectView;
   	
	}

	@Tag(name = "Les clients - Mis à jour Client pour le client central")
    @PostMapping(value = "/lesclients/trouve/{id}")
   	public Optional<Client>  trouveLeClient(@RequestParam int id){
		
   		Optional<Client> client =clientDao.findById(id);

		return client;
   	
	}
	
	@GetMapping("/user/registration")
	public ModelAndView showRegistrationForm(WebRequest request, Model model) {
	    UserDto userDto = new UserDto();
	    userDto.setFirstName("John");
	    userDto.setLastName("Doe");
	    model.addAttribute("user", userDto);
	    ModelAndView modelAndView = new ModelAndView();
		   
	    modelAndView.setViewName("registration");	

	    return modelAndView;
	    
	   // return "registration";
	}
}

