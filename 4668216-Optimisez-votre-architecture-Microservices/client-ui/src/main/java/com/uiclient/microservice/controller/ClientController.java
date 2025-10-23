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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

import com.uiclient.microservice.beans.CommandeBean;
import com.uiclient.microservice.beans.ProductBean;
import com.uiclient.microservice.proxies.MicroserviceCommandeProxy;
import com.uiclient.microservice.proxies.MicroserviceProduitsProxy;


@Controller
public class ClientController {
	
	   private final MicroserviceProduitsProxy produitsProxy;
	   
	   private final MicroserviceCommandeProxy commandeProxy;
	   
	   	   
	   public ClientController(MicroserviceProduitsProxy produitsProxy,MicroserviceCommandeProxy commandeProxy)
	   {
	       this.produitsProxy = produitsProxy;
		   this.commandeProxy = commandeProxy;
	      
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
	  
	  @PostMapping(value = "/commandes/passecommande/{id}")//@PathVariable 
	  public RedirectView passerUneCommande(@RequestParam int id,@Validated CommandeBean commande){
		  LocalDateTime now = LocalDateTime.now();
		  DateTimeFormatter dtf = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
		  dtf.format(now);
		  commande.setDateCommande(now);
		 commandeProxy.passerUneCommande(id,commande);
		 return new RedirectView("/");
		 }

}
