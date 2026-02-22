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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.view.RedirectView;

import com.uiclient.microservice.dto.ClientBean;
import com.uiclient.microservice.dto.CommandeBean;
import com.uiclient.microservice.dto.ExpeditionBean;
import com.uiclient.microservice.dto.ProductBean;
import com.uiclient.microservice.proxies.MicroserviceClientProxy;
import com.uiclient.microservice.proxies.MicroserviceCommandeProxy;
import com.uiclient.microservice.proxies.MicroserviceExpeditionProxy;
import com.uiclient.microservice.proxies.MicroserviceProduitsProxy;

import jakarta.ws.rs.core.MediaType;


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
	  
	  @GetMapping("/produits")
	  public String produits(Model model)
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
	  @GetMapping("/details-produit/commande/{id}")
	  public String formCommandeProduit(@PathVariable int id,  Model model) {
		  
//		  CommandeBean commandeBean = commandeProxy.recupererUneCommande(id);
		  ProductBean produit = produitsProxy.recupererUnProduit(id);
		  CommandeBean commande = new CommandeBean();
		  //commande.setId(1);
		  commande.setProductId(produit.getId());
		  commande.setTitre(produit.getTitre());
		  commande.setPrix(produit.getPrix());
		  LocalDateTime now = LocalDateTime.now();
		  DateTimeFormatter dtf = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
		  dtf.format(now);

//		  commandeBean.setDateCommande(now);
		  model.addAttribute("produit", produit);
		  model.addAttribute("commande", commande);
		  return "CommanderProduit";

	  }

	  @GetMapping("/lescommandes")
	  public String lesCommandes(Model model)
	  {
	       List<CommandeBean> commandes =  commandeProxy.lesCommandes();
	       for (CommandeBean commande : commandes) {
	           ProductBean produit = produitsProxy.recupererUnProduit(commande.getProductId());
	           
	           commande.setTitre(produit.getTitre());
	           commande.setPrix(produit.getPrix()*commande.getQuantite());
	       }
	       model.addAttribute("commandes",commandes);

	      return "Commandes";

	  }

	  @GetMapping("/lesclients")
	  public String lesClients(Model model)
	  {
		  Iterable<ClientBean> clients =  clientProxy.lesClients();
	       model.addAttribute("clients",clients);

	      return "Clients";

	  }	  
	  
	  @PostMapping("/lesclients/del/{id}")
	  public RedirectView deleteLesClientById(@PathVariable int id)
	  {
		  clientProxy.deleteLesClientById(id);
		  RedirectView redirectView = new RedirectView();
		  redirectView.setUrl("/lesclients");

		  return redirectView;
	
	  }
	  
	  
	  @PostMapping(value="/lesclients/maj/{id}",consumes = MediaType.APPLICATION_JSON) // consumes = {"*/*"} ,consumes = MediaType.APPLICATION_FORM_URLENCODED
	  public  RedirectView upClient(@RequestParam int id,@RequestBody ClientBean client)
	  {
		  clientProxy.majLeClient(id, client);

		  RedirectView redirectView = new RedirectView();
		  redirectView.setUrl("/lesclients");

		  return redirectView;

	  }
	  
	  @PostMapping(value="/lesclients/trouve/{id}")
	  public String trouveClient(@PathVariable int id,Model model)
	  {
		  ClientBean client = clientProxy.trouveLeClient(id);

	      model.addAttribute("client",client);

	      return "MajClient";

	  }
	  
//	  @PostMapping(value="/majclient/{id}")
//	  public String majClient(@PathVariable int id,@RequestBody ClientBean client, Model model) {
//		  clientProxy.majLeClient(id,client);
//		  model.addAttribute("client", client);
//		  
//		  return "Clients";
//		}
	  
	  @PostMapping(value = "/commandes/passecommande/{id}")
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
	    commande.setTitre(produit.getTitre());
	    model.addAttribute("commande", commande);
	    model.addAttribute("produit", produit);
	    return "MajCommande";
	  }

	  @PostMapping (value="/lescommandes/maj/{id}",consumes = MediaType.APPLICATION_JSON)
	  public RedirectView miseAJourDuneCommande(@PathVariable int id,@Validated CommandeBean commande) {
		  
		  LocalDateTime now = LocalDateTime.now();
		  DateTimeFormatter dtf = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
		  dtf.format(now);
		  
		  if(commande.getProductId()>0) {
              ProductBean produit = produitsProxy.recupererUnProduit(commande.getProductId());
              commande.setTitre(produit.getTitre());
              commande.setPrix(produit.getPrix());
              commande.setDateCommande(now);
              commandeProxy.miseAJourDuneCommande(id,commande);
          }

		  
		  return new RedirectView("/lescommandes");
	  }

	  @PostMapping("/lescommandes/delete/{id}")
	  public RedirectView deleteCommanById(@PathVariable int id){
		  commandeProxy.supprimmeUneCommande(id);
		  return new RedirectView("/");

	  }

}
