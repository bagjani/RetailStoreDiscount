package com.test.RetailEntity;

import java.util.HashMap;
import java.util.Map;

import com.test.PricingDiscount.IDiscount;
import com.test.PricingDiscount.ProductPricing;
import com.test.RetailEntity.Customer.CustomerType;

public class Cart {

	private Map<IProductDetails, Integer> quantities;
    private IDiscount discountPolicy;
    private Customer customer;

    public Cart(Customer customer) {
        quantities = new HashMap<IProductDetails, Integer>();
        this.customer = customer;
    }
    
	public Cart(Customer customer, IDiscount discountPolicy) {
        quantities = new HashMap<IProductDetails, Integer>();
        this.customer = customer;
        this.discountPolicy = discountPolicy;
    }

    public double total() {
        double result = 0;
        for (IProductDetails each : quantities.keySet()) {
            result += each.priceForQuantity(quantities.get(each));
        }
        
        if (discountPolicy != null) {
        	result = discountPolicy.applyDiscount(result);
        }
        
        return result;
    }

    public void add(IProductDetails itemToBuy) {    	
        add(itemToBuy, 1);
    }
    

    // To add multiple quantities of item
    public void add(IProductDetails item, int qnty) {
    	
    	if(customer.getCustomerType() == CustomerType.EMPLOYEE || customer.getCustomerType() == CustomerType.AFFILIATE) {
    		item = new ProductPricing(item, customer.getCustomerType().getDiscountPercentage());
    	}else if(customer.isAvailableForLoyaltyDiscount()) {
    		item = new ProductPricing(item, CustomerType.CUSTOMER.getDiscountPercentage());
    	}
    	
        int previousQuantity = quantities.containsKey(item)
                ? quantities.get(item) : 0;
        if(qnty>0)
        	quantities.put(item, previousQuantity + qnty);
        else
        	throw new IllegalArgumentException("quantity must be in positive integer");
    }
    
    public Map<IProductDetails, Integer> getQuantities() {
		return quantities;
	}

	public void setQuantities(Map<IProductDetails, Integer> quantities) {
		this.quantities = quantities;
	}
}
