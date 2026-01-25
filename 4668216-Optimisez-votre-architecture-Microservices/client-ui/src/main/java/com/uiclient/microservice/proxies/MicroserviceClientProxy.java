package com.uiclient.microservice.proxies;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.uiclient.microservice.beans.ClientBean;

@FeignClient(name = "com.mclients",url = "http://localhost:9005")

@RibbonClient(name = "com.mclients")

public interface MicroserviceClientProxy {

    @GetMapping(value = "/lesclients")
    public Iterable<ClientBean> lesClients();
    
    @PostMapping(value = "/lesclients/find/{id}")//@RequestParam  int id,@RequestBody CommandeBean commande
    public ClientBean  findLeClient(@RequestParam  int id);
    
    @PostMapping(value = "/lesclients/maj/{id}")
    public ClientBean majLeClient(@RequestParam  int id,@RequestBody ClientBean client) ;
    
    @DeleteMapping(value = "/lesclients/del/{id}")
    public void  deleteLesClientById(@PathVariable int id);

	
}
