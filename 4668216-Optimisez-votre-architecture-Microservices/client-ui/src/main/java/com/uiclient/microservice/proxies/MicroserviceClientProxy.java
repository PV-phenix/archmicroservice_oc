package com.uiclient.microservice.proxies;

import java.util.List;

import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import com.uiclient.microservice.beans.ClientBean;

@FeignClient(name = "com.mclients",url = "http://localhost:9005")

@RibbonClient(name = "com.mclients")

public interface MicroserviceClientProxy {

    @GetMapping(value = "/lesclients")
    public List<ClientBean> lesClients();
}
