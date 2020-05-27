package com.foodcart.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.foodcart.model.FoodType;
@RepositoryRestResource(collectionResourceRel = "foodTypes" ,path="food-types") //overriding rest end points
public interface FoodTypeRepository extends JpaRepository<FoodType, Integer> {

}
