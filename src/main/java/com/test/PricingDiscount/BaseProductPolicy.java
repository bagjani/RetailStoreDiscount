package com.test.PricingDiscount;

import com.test.RetailEntity.IProductDetails;

public class BaseProductPolicy implements IProductDetails {
	private final IProductDetails baseProduct;

	public BaseProductPolicy(IProductDetails baseProduct) {
		this.baseProduct = baseProduct;
	}

	public double getUnitPrice() {
		return baseProduct.getUnitPrice();
	}

	public String getName() {
		return baseProduct.getName();
	}

	public ItemType getType() {
		return baseProduct.getType();
	}

	public double priceForQuantity(int quantity) {
		return baseProduct.priceForQuantity(quantity);
	}
}
