package com.foodcart.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.foodcart.model.FoodDetails;

public interface FoodDetailsRepository extends JpaRepository<FoodDetails, Integer>{

}
