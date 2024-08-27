package com.mcommerce.mexpedition.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.mcommerce.mexpedition.dao.ExpeditionDao;
import com.mcommerce.mexpedition.exception.ExpeditionNotFoundException;
import com.mcommerce.mexpedition.model.Expedition;



@RestController // Si on met @Controller alors on voit les templates en visuel
public class ExpeditionController {
	
	@Autowired
	ExpeditionDao expeditionDao;
	
	@GetMapping("/")
	public ModelAndView AccueilExpeForm(Model model) {
		
	       List<Expedition> expeditions =  expeditionDao.findAll();
	       
	       model.addAttribute("expeditions", expeditions);
	       ModelAndView modelAndView = new ModelAndView();
	       modelAndView.setViewName("accueil");	

	       return modelAndView	;//Ne pas oublier Thymeleaf
	}
	
	@GetMapping("/addexp")
	public ModelAndView AjouterExpedition(){
		
	       ModelAndView modelAndView = new ModelAndView();
	       modelAndView.setViewName("ajoutexpedition");	

	       return modelAndView;	
		
		}
	
	@PostMapping("/addexp")
	public ModelAndView AjouterExpedition(@Validated Expedition expedition) throws Exception{
		//Expedition expedition = new Expedition(2,2,"En attente");
		expeditionDao.saveAndFlush(expedition);
		
	       ModelAndView modelAndView = new ModelAndView();
	       modelAndView.setViewName("../accueil");	
	       return modelAndView;	
		
		
		}
	
	@PostMapping("/delete/{id}")
	public ModelAndView GetExpeditionById(@PathVariable int id) {
		//Optional<Expedition> expedition = expeditionDao.findById(id);
		expeditionDao.deleteById(id);
		expeditionDao.flush();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("accueil");
		return modelAndView ; 
		}

	@PostMapping("/update/{id}")
	public ModelAndView DeleteExpeditionB(@PathVariable int id,Model model) {
		
		Expedition expedition = expeditionDao.findById(id).orElseGet(null);
				
		model.addAttribute("expedition",expedition);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("majexp");
		
		return modelAndView ; 
		}
	
	@PostMapping("/majexp/{id}")
	public ModelAndView DeleteExpeditionById(@PathVariable int id,@Validated Expedition expedition) {
			
		expeditionDao.saveAndFlush(expedition);
	       ModelAndView modelAndView = new ModelAndView();
	       modelAndView.setViewName("accueil");	

	       return modelAndView;
		
		}
	
	  @GetMapping("/expeditions")
	  public List<Expedition> LesExpeditions( Model model){
		  List<Expedition> expeditions = expeditionDao.findAll();
		  if(expeditions.isEmpty()) throw new ExpeditionNotFoundException("Aucune livraison !");
	    return expeditions;
	  }
	  
	   @GetMapping("/expeditions/{id}")
	   Expedition recupererUneExpedition(@PathVariable int id) {
		   Expedition expedition = expeditionDao.findById(id).orElseGet(null);;
		return expedition;
		}
}
