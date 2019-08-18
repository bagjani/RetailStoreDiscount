package com.test.RetailEntity;

public class Product implements IProductDetails{
	
	private final String name;
    private final double unitPrice;
    private final ItemType type;

    public Product(String name, double priceInDollars, ItemType type) {
        this.name = name;
        this.unitPrice = priceInDollars;
        this.type = type;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public String getName() {
        return name;
    }

    public double priceForQuantity(int quantity) {
        return unitPrice * quantity;
    }
    
    public ItemType getType() {
    	return type;
    }
}
