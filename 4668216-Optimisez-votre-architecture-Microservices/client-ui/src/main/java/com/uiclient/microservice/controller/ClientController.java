	package com.uiclient.microservice.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;

import com.uiclient.microservice.beans.CommandeBean;
import com.uiclient.microservice.beans.ExpeditionBean;
import com.uiclient.microservice.beans.ClientBean;
import com.uiclient.microservice.beans.ProductBean;
import com.uiclient.microservice.proxies.MicroserviceClientProxy;
import com.uiclient.microservice.proxies.MicroserviceCommandeProxy;
import com.uiclient.microservice.proxies.MicroserviceExpeditionProxy;
import com.uiclient.microservice.proxies.MicroserviceProduitsProxy;


@Controller
public class ClientController {
	
	   private final MicroserviceProduitsProxy produitsProxy;
	   
	   private final MicroserviceCommandeProxy commandeProxy;
	   
	   private final MicroserviceExpeditionProxy expeditionProxy;
	   
	   private final MicroserviceClientProxy clientProxy;
	   
	   	   
	   public ClientController(MicroserviceProduitsProxy produitsProxy,MicroserviceCommandeProxy commandeProxy,MicroserviceExpeditionProxy expeditionProxy,MicroserviceClientProxy clientProxy)
	   {
	       this.produitsProxy = produitsProxy;
		   this.commandeProxy = commandeProxy;
		   this.expeditionProxy = expeditionProxy;
		   this.clientProxy = clientProxy;
	      
	   }
	   
	  @GetMapping("/")
	  public String accueil(Model model)
	  {
	       List<ProductBean> produits =  produitsProxy.listeDesProduits();
	       model.addAttribute("produits", produits);

	      return "Accueil";

	  }
	  
	  @GetMapping("/details-produit/{id}")
	  public String ficheProduit(@PathVariable int id,  Model model){
	    ProductBean produit = produitsProxy.recupererUnProduit(id);
	    model.addAttribute("produit", produit);
	    return "FicheProduit";
	  }
	  
	  
	  
	  //Passer une commande d'un produit par son id
	  @GetMapping("/details-produit/commander-produit/{id}")
	  public String formCommandeProduit(@PathVariable int id,  Model model) {
		  ProductBean produit = produitsProxy.recupererUnProduit(id);
		  CommandeBean commande = new CommandeBean();
		  //commande.setId(1);
		  commande.setProductId(produit.getId());
		  commande.setTitre(produit.getTitre());
		  commande.setPrix(produit.getPrix());

		  LocalDateTime now = LocalDateTime.now();
		  DateTimeFormatter dtf = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
		  dtf.format(now);

		  commande.setDateCommande(now);
		  model.addAttribute("produit", produit);
		  model.addAttribute("commande", commande);
		  return "CommanderProduit";

	  }
	  
	  @GetMapping("/lescommandes")
	  public String lesCommandes(Model model)
	  {
	       List<CommandeBean> commandes =  commandeProxy.lesCommandes();
	       model.addAttribute("commandes",commandes);

	      return "Commandes";

	  }
	  
	  @GetMapping("/lesclients")
	  public String lesClients(Model model)
	  {
	       List<ClientBean> clients =  clientProxy.lesClients();
	       model.addAttribute("clients",clients);

	      return "Clients";

	  }
	  @PostMapping(value = "/commandes/passecommande/{id}")//@PathVariable 
	  public RedirectView passerUneCommande(@PathVariable int id,@Validated CommandeBean commande){
		  LocalDateTime now = LocalDateTime.now();
		  DateTimeFormatter dtf = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
		  dtf.format(now);
		  commande.setDateCommande(now);
		 commandeProxy.passerUneCommande(id,commande);
		 return new RedirectView("/");
		 }
	  
	  @GetMapping("/expeditions")
	  public String lesExpeditions(Model model)
	  {
	       List<ExpeditionBean> expeditions =  expeditionProxy.toutesLesExpeditions();
	       model.addAttribute("expeditions", expeditions);

	      return "Expedition";

	  }
	  
	  @PostMapping(value = "/lescommandes/{id}")
	  public String CommandeById(@PathVariable int id,Model model) {
	    CommandeBean commande = commandeProxy.recupererUneCommande(id);
	    ProductBean produit = produitsProxy.recupererUnProduit(commande.getProductId());
	    model.addAttribute("commande", commande);
	    model.addAttribute("produit", produit);
	    return "MajCommande";
	  }
	  
	  @PostMapping (value="/lescommandes/maj/{id}")
	  public RedirectView miseAJourDuneCommande(@PathVariable int id,@Validated CommandeBean commande) {
		  LocalDateTime now = LocalDateTime.now();
		  DateTimeFormatter dtf = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
		  dtf.format(now);
		  commande.setDateCommande(now);
		  
		  commandeProxy.miseAJourDuneCommande(id,commande);
		  return new RedirectView("/");
	  }
	  
	  @PostMapping("/lescommandes/delete/{id}")
	  public RedirectView deleteCommanById(@PathVariable int id){
		  commandeProxy.supprimmeUneCommande(id);
		  return new RedirectView("/");
		  
	  }
	  

}
