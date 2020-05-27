package com.foodcart.config;

import javax.persistence.EntityManager;
import javax.persistence.metamodel.Type;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.data.rest.webmvc.config.RepositoryRestConfigurer;

import com.foodcart.model.FoodDetails;
import com.foodcart.model.FoodType;


@Configuration
public class RepositoryConfig implements RepositoryRestConfigurer{

	@Autowired
	EntityManager entityManager;
	
	@Override
	public void configureRepositoryRestConfiguration(RepositoryRestConfiguration config) {
		//config.exposeIdsFor(FoodDetails.class); //ids exposed for the food details table
		//config.exposeIdsFor(FoodType.class);//ids exposed for the food category table
		
		//we can auto expose ids instead of doing it manualyy
		//create entity manager ,get all entities and loop through all the entities
		config.exposeIdsFor(entityManager.getMetamodel().getEntities().stream().map(Type::getJavaType).toArray(Class[]::new));
				
				
		
	}

	
	
}
