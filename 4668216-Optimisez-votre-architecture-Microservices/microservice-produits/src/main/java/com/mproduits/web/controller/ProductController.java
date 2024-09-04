package com.mproduits.web.controller;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.mproduits.dao.ProductDao;

import com.mproduits.handler.ApplicationPropertiesConfiguration;
import com.mproduits.model.Product;
import com.mproduits.web.exceptions.ProductNotFoundException;


//import com.mproduits.handler.SimpleLoggingHandler;

//import io.micrometer.observation.Observation;
//import io.micrometer.observation.ObservationRegistry;

@RestController
public class ProductController  implements HealthIndicator  {

    @Autowired
    ProductDao productDao;
    private final ApplicationPropertiesConfiguration appProperties;
    
    Logger log = LoggerFactory.getLogger(this.getClass());
    

//    ObservationRegistry observationRegistry = ObservationRegistry.create();
//    Observation observation = Observation.createNotStarted("sample", observationRegistry);


    
    
    public ProductController(ProductDao productDao, ApplicationPropertiesConfiguration appProperties){
    	this.productDao = productDao;
    	this.appProperties = appProperties;
    	}

    // Affiche la liste de tous les produits disponibles
    @GetMapping(value = "/Produits")
    public List<Product> listeDesProduits(){
    	
    	
//    	observationRegistry.observationConfig().observationHandler(new SimpleLoggingHandler());


    	List<Product> listeLimitee =null;
//    	observation.start();
//    	try (Observation.Scope scope = observation.openScope()) {
//    	    // ... the observed action
            List<Product> products = productDao.findAll();
            
            if(products.isEmpty()) throw new ProductNotFoundException("Aucun produit n'est disponible à la vente");

               listeLimitee = products.subList(0, appProperties.getLimitDeProduits());
               // return products;

       
//    	} catch (Exception e) {
//    	    observation.error(e);
//    	    // further exception handling
//    	} finally {
//    	    observation.stop();
//    	}
               
        log.info("Récupération de la liste des produits");

        return listeLimitee; 

    }

    //Récuperer un produit par son id
    @GetMapping( value = "/Produits/{id}")
    public Optional<Product> recupererUnProduit(@PathVariable int id) {

        Optional<Product> product = productDao.findById(id);

        if(product.isEmpty())  throw new ProductNotFoundException("Le produit correspondant à l'id " + id + " n'existe pas");

        return product;
    }

	@Override
	public Health health() {
		// TODO Auto-generated method stub
		List<Product> products = productDao.findAll();
		if(products.isEmpty()) 
		{
		   return Health.down().build();
		}
		return Health.up().build();
	}
}

