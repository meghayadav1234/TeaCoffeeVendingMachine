package com.yash.dto;

public class ContainerMaterialDTO {

	private Integer tea;

	private Integer coffee;

	private Integer sugar;

	private Integer water;

	private Integer milk;
	
	private Integer cupCount;
	
	private Integer totalPrice;

	public ContainerMaterialDTO(Integer tea, Integer coffee, Integer sugar, Integer water, Integer milk) {
		super();
		this.tea = tea;
		this.coffee = coffee;
		this.sugar = sugar;
		this.water = water;
		this.milk = milk;
	}

	public Integer getTea() {
		return tea;
	}

	public Integer getCoffee() {
		return coffee;
	}

	public Integer getSugar() {
		return sugar;
	}

	public Integer getWater() {
		return water;
	}

	public Integer getMilk() {
		return milk;
	}

	public Integer getCupCount() {
		return cupCount;
	}

	public void setCupCount(Integer cupCount) {
		this.cupCount = cupCount;
	}

	public Integer getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(Integer totalPrice) {
		this.totalPrice = totalPrice;
	}
}
