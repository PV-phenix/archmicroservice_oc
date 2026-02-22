package com.uiclient.microservice.proxies;

import java.util.List;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.uiclient.microservice.dto.ExpeditionBean;

@FeignClient(name = "mexpedition",url = "http://localhost:9006")

@RibbonClient(name = "mexpedition")

public interface MicroserviceExpeditionProxy {

	   @GetMapping(value = "/expeditions")
	   List<ExpeditionBean> toutesLesExpeditions();

	   @GetMapping(value = "/expeditions/{id}")
	   ExpeditionBean recupererUneExpedition(@PathVariable int id);
}
