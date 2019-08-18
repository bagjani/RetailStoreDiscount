package com.test.PricingDiscount;

import com.test.RetailEntity.IProductDetails;

public class ProductPricing extends BaseProductPolicy {
	
	private final double priceFactor;

	public ProductPricing(IProductDetails baseProduct, int percentPromotion) {
		super(baseProduct);
		if (percentPromotion < 0 || percentPromotion > 100) {
			throw new IllegalArgumentException("percentPromotion must be within 0 to 100");
		}
		this.priceFactor = (100 - percentPromotion) / 100.0;
	}

	@Override
	public double priceForQuantity(int quantity) {
		// If Grocery then don't apply the percentage discount
		if (super.getType() == ItemType.GROCERY) {
			return super.priceForQuantity(quantity);
		}

		// else apply percentage discount
		return (super.priceForQuantity(quantity) * priceFactor);
	}
}
