package com.test.RetailEntity;

public interface IProductDetails {
	
	public enum ItemType {
		GROCERY,
		OTHER;
	}

	double getUnitPrice();

	double priceForQuantity(int quantity);

	String getName();

	ItemType getType();

}