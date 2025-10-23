 package com.uiclient.microservice.proxies;

import java.util.List;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.uiclient.microservice.beans.CommandeBean;

@FeignClient(name = "com.mcommandes",url = "http://localhost:9002")

@RibbonClient(name = "com.mcommandes")

public interface MicroserviceCommandeProxy {

    @PostMapping(value = "/commandes/add")
    public CommandeBean ajouterCommande(@RequestBody CommandeBean commande);
    
    @GetMapping(value = "/lescommandes")
    public List<CommandeBean> lesCommandes();
    
    @GetMapping(value = "/commandes/{id}")
    public CommandeBean recupererUneCommande(@PathVariable int id);
    
    @PostMapping(value = "/commandes/passecommande/{id}")
    public void passerUneCommande(@RequestParam  int id,@Validated CommandeBean commande);
}
