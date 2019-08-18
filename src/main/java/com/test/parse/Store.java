package com.test.parse;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import com.test.PricingDiscount.IDiscount;
import com.test.PricingDiscount.OfferDiscount;
import com.test.RetailEntity.Cart;
import com.test.RetailEntity.Customer;
import com.test.RetailEntity.Customer.CustomerType;
import com.test.RetailEntity.IProductDetails;
import com.test.RetailEntity.IProductDetails.ItemType;
import com.test.RetailEntity.Product;
	
public class Store {

	Map<String, Customer> customerMap;
	Map<String, IProductDetails> productMap;
	Map<String, Cart> cartMap;

	public Store() {
		customerMap = new HashMap<String, Customer>();
		productMap = new HashMap<String, IProductDetails>();
		cartMap = new HashMap<String, Cart>();
	}

	public void addCustomer(String customerType, String name, String date) {
		SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
		Date dateTime = null;
		try {
			dateTime = formatter.parse(date);
		} catch (ParseException e) {
			System.out.println("Invalid date");
		}
		Customer cust = new Customer(CustomerType.valueOf(customerType), name, dateTime);
		customerMap.put(name, cust);
		System.out.println("Customer added successfully");
	}

	public void addProduct(String productName, Double price, String type) {
		IProductDetails item = new Product(productName, price, ItemType.valueOf(type));
		if (productMap.get(productName) == null) {
			productMap.put(productName, item);
			System.out.println("Product Added : " + productName);
		} else
			System.out.println("Product already there");
	}

	public void showAllProducts() {
		System.out.println("Products:");
		for (Map.Entry entry : productMap.entrySet()) {
			IProductDetails item = (IProductDetails) entry.getValue();
			System.out.println(
					"Name : " + item.getName()+ " " + "Type : " + item.getType() + " "  + "Unit Price : " + item.getUnitPrice());
		}
	}

	public void addProductToCart(String custName, String productName, Integer quantity) {
		Cart cart = null;
		if(cartMap.get(custName) == null) {
			System.out.println("Please add cart first");
		}else {
			cart = cartMap.get(custName);
			IProductDetails item = null;
			if(productMap.get(productName) == null)
				System.out.println("Please add the product first via add_product <product_name>, <price> <product_type>");
			else {
				item = productMap.get(productName);
				if (quantity == 0) {
					cart.add(item);
				}else {
					cart.add(item, quantity);
				}
				cartMap.put(custName, cart);
				System.out.println("1 Item added to cart of customer " + custName);
				System.out.println("Current items are:");
					for(Entry<IProductDetails, Integer> cartItem : cart.getQuantities().entrySet()) {
						System.out.println( "Item Name : " + ((IProductDetails)cartItem.getKey()).getName() + " - Item quantity :" + cartItem.getValue());
					}
			}
		}
	}

	public void createCart(String custName, Boolean isDiscountPolicy) {
		IDiscount discountPolicy = null;
		if (isDiscountPolicy)
			discountPolicy = new OfferDiscount();
		Customer cust = customerMap.get(custName);
		Cart cart = new Cart(cust, discountPolicy);
		cartMap.put(custName, cart);
		System.out.println("Cart created for : " + custName);
	}

	public void totalBill(String custName) {
		System.out.println("Total bill for " + custName + ": " + cartMap.get(custName).total());
	}

}
