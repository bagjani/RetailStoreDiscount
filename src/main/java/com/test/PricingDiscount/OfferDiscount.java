package com.test.PricingDiscount;

public class OfferDiscount implements IDiscount{
	@Override
	public double applyDiscount(double totalAmount) {
		if (totalAmount < 100)
			return totalAmount;
		double discPerc = (double) totalAmount / 100;
		double disc = discPerc * 5;
		return totalAmount - disc; 
	}
}
