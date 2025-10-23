package com.mcommandes.web.controller;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.mcommandes.dao.CommandesDao;
import com.mcommandes.model.Commande;
import com.mcommandes.web.exceptions.CommandeNotFoundException;
import com.mcommandes.web.exceptions.ImpossibleAjouterCommandeException;

@RestController
public class CommandeController {

    @Autowired
    CommandesDao commandesDao;

    @PostMapping ("/commandes")
    public ResponseEntity<Commande> ajouterCommande(@RequestBody Commande commande){

        Commande nouvelleCommande = commandesDao.save(commande);

        if(nouvelleCommande == null) throw new ImpossibleAjouterCommandeException("Impossible d'ajouter cette commande");

        return new ResponseEntity<Commande>(commande, HttpStatus.CREATED);
    }

    @GetMapping("/commandes/{id}")
    public Optional<Commande> recupererUneCommande(@PathVariable int id){

        Optional<Commande> commande = commandesDao.findById(id);

        if(commande.isEmpty()) throw new CommandeNotFoundException("Cette commande n'existe pas");

        return commande;
    }
    
    @GetMapping("/lescommandes")
    public List<Commande> lesCommandes(){
      	List<Commande>commandes =commandesDao.findAll();
	    return commandes;
    	
    }
    
    @GetMapping("/commandes")
    public ModelAndView toutesLesCommande(Model model){
    	List<Commande>commandes =commandesDao.findAll();
	    model.addAttribute("commandes", commandes);
	    ModelAndView modelAndView = new ModelAndView();
	   
	    modelAndView.setViewName("Accueil");	

	    return modelAndView;
    	
    }

    /*
    * Permet de mettre à jour une commande existante.
    * save() mettra à jours uniquement les champs renseign�s dans l'objet commande re�u. Ainsi dans ce cas, comme le champs date dans "commande" n'est
    * pas renseign�, la date pr�c�demment enregistr�e restera en place
    **/
    @PutMapping("/commandes")
    public void updateCommande(@RequestBody Commande commande) {

        commandesDao.save(commande);
    }
    @PostMapping("/commandes/passecommande/{id}")
    //public  ModelAndView passerUneCommande(@PathVariable int id,@Validated Commande commande){
    public  void passerUneCommande(@RequestParam int id,@RequestBody Commande commande){
    	
    	LocalDateTime now = LocalDateTime.now();
		DateTimeFormatter dtf = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
		dtf.format(now);
		commande.setDateCommande(now);
		commande.setCommandePayee(true);
		//commande.setProductId(2);
		//commande.setQuantite(4);
    	commandesDao.saveAndFlush(commande);
    	
    	
    	
//    	ModelAndView modelAndView = new ModelAndView();
//    	modelAndView.setViewName("/");
    	//modelAndView.setViewName("Accueil");

//		return modelAndView;
    	
    }
    
	@PostMapping("/commandes/delete/{id}")
	public ModelAndView GetExpeditionById(@PathVariable int id) {
		//Optional<Expedition> expedition = expeditionDao.findById(id);
		commandesDao.deleteById(id);
		commandesDao.flush();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/commandes");
		return modelAndView ; 
		}
}
