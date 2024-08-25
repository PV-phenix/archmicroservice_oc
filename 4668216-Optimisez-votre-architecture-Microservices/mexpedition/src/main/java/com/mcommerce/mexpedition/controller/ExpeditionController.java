package com.mcommerce.mexpedition.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import com.mcommerce.mexpedition.dao.ExpeditionDao;
import com.mcommerce.mexpedition.model.Expedition;


@Controller
public class ExpeditionController {
	
	@Autowired
	ExpeditionDao expeditionDao;
	
	@GetMapping("/")
	public String AccueilExpeForm(Model model) {
	       List<Expedition> expeditions =  expeditionDao.findAll();
	       model.addAttribute("expeditions", expeditions);
	       

		return "accueil";//Ne pas oublier Thymeleaf
	}
	
	@GetMapping("/addexp")
	public String AjouterExpedition(){
		//Expedition expedition = new Expedition(2,2,"En attente");
//		expeditionDao.save(expedition);
//		
		return "ajoutexpedition";
		}
	
	@PostMapping("/addexp")
	public String AjouterExpedition(@Validated Expedition expedition){
		//Expedition expedition = new Expedition(2,2,"En attente");
		expeditionDao.saveAndFlush(expedition);
		
		return "redirect:/";
		
		
		}
	
	@PostMapping("/delete/{id}")
	public String GetExpeditionById(@PathVariable int id) {
		//Optional<Expedition> expedition = expeditionDao.findById(id);
		expeditionDao.deleteById(id);
		expeditionDao.flush();
		return "redirect:/" ; 
		}

	@PostMapping("/update/{id}")
	public String DeleteExpeditionB(@PathVariable int id,Model model) {
		
		Expedition expedition = expeditionDao.findById(id).orElseGet(null);
				
		model.addAttribute("expedition",expedition);
		return "majexp" ; 
		}
	
	@PostMapping("/majexp/{id}")
	public String DeleteExpeditionById(@PathVariable int id,@Validated Expedition expedition) {
		
		
		expeditionDao.saveAndFlush(expedition);
		return "redirect:/" ; 
		}
}
