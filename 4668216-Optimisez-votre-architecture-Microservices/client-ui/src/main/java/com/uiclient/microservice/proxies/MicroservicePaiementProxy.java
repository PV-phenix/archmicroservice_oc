package com.uiclient.microservice.proxies;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.uiclient.microservice.dto.PaiementBean;

@FeignClient(name = "mpaiement")

@RibbonClient(name = "mpaiement")

public interface MicroservicePaiementProxy {

    @PostMapping(value = "/paiement")
    ResponseEntity<PaiementBean> payerUneCommande(@RequestBody PaiementBean paiement);

}
