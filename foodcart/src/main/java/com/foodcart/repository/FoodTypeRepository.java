package com.foodcart.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.foodcart.model.FoodType;
//@CrossOrigin(origins = "*")//any request coming from other sources u need to allow 
@RepositoryRestResource(collectionResourceRel = "foodTypes" ,path="food-types") //overriding rest end points
public interface FoodTypeRepository extends JpaRepository<FoodType, Integer> {

}
