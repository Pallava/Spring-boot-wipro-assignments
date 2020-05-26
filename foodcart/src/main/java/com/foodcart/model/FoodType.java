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

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name="food_category")
@Getter
@Setter
@ToString
public class FoodType {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name ="category_id")
	private Integer id;
	@Column(name="food_category")
	private String foodCategory;
	
	@OneToMany(cascade = CascadeType.ALL,mappedBy = "foodType")
	private List<FoodDetails> foodDetails;

}
