package com.foodcart.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.foodcart.model.FoodDetails;
@CrossOrigin(origins = "*")//any request coming from other sources u need to allow 
@RepositoryRestResource(collectionResourceRel = "foodDetailses" ,path="food-details") //overriding rest end points
public interface FoodDetailsRepository extends JpaRepository<FoodDetails, Integer>{

	@RestResource(path = "foodCategoryId")
	Page <FoodDetails> findByFoodTypeId(@Param("id") Integer categoryId,Pageable pageable);
}
