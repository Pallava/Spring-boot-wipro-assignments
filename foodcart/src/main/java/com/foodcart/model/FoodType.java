package com.foodcart.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="food_category")
public class FoodType {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name ="category_id")
	private Integer id;
	@Column(name="food_category")
	private String foodCategory;
	
	@OneToMany(cascade = CascadeType.ALL,mappedBy = "foodType")
	private List<FoodDetails> foodDetails;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getFoodCategory() {
		return foodCategory;
	}

	public void setFoodCategory(String foodCategory) {
		this.foodCategory = foodCategory;
	}

	public List<FoodDetails> getFoodDetails() {
		return foodDetails;
	}

	public void setFoodDetails(List<FoodDetails> foodDetails) {
		this.foodDetails = foodDetails;
	}

	
	
}
