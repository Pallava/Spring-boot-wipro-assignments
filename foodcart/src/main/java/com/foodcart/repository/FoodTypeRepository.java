package com.foodcart.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.foodcart.model.FoodType;

public interface FoodTypeRepository extends JpaRepository<FoodType, Integer> {

}
