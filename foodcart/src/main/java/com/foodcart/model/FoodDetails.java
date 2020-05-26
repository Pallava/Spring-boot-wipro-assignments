package com.foodcart.model;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name="food_details")
@Getter
@Setter
@ToString
public class FoodDetails {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="food_details_id")
	private Integer id;
	@Column(name="name")
	private String name;
	@Column(name="description")
	private String description;
	@Column(name="unit_price")
	private BigDecimal unitPrice;
	@Column(name="img_url")
	private String imageUrl;
	@Column(name="units_in_stock")
	private int unitsInStock;
	@Column(name="cre_datt")
	private Date createdOn;
	@Column(name="mod_datt")
	private Date updatedOn;
	
	@ManyToOne
	@JoinColumn(name = "category_id",nullable = false)
	private FoodType foodType;

}
