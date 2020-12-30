package com.bharath.aws.lamdba.apis.dto;

public class Order {
	public int id;
	public String itemName;
	public int quantity;

	public Order() {

	}

	public Order(int id, String itemName, int quantity) {
		this.id = id;
		this.itemName = itemName;
		this.quantity = quantity;
	}
}
