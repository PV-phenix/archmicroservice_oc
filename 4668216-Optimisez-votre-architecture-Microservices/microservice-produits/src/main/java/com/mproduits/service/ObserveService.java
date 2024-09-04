package com.mproduits.service;

import org.springframework.stereotype.Service;

import io.micrometer.observation.Observation;
import io.micrometer.observation.ObservationRegistry;

@Service
public class ObserveService {
	
    private ObservationRegistry observationRegistry;

    // constructor

    public String sayHello() {
        return Observation
          .createNotStarted("ObserveService", observationRegistry)
          .observe(this::sayHelloNoObserver);
    }

    private String sayHelloNoObserver() {
        return "Hello World!";
    }

}
