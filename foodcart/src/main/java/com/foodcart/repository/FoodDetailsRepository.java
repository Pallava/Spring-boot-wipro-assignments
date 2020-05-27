package com.foodcart.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.foodcart.model.FoodDetails;
@RepositoryRestResource(collectionResourceRel = "foodDetailses" ,path="food-details") //overriding rest end points
public interface FoodDetailsRepository extends JpaRepository<FoodDetails, Integer>{

}
