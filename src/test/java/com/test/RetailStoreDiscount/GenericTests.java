package com.test.RetailStoreDiscount;

import static org.junit.Assert.assertEquals;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;

import com.test.RetailEntity.Cart;
import com.test.RetailEntity.Customer;
import com.test.RetailEntity.Customer.CustomerType;
import com.test.RetailEntity.IProductDetails;
import com.test.RetailEntity.IProductDetails.ItemType;
import com.test.RetailEntity.Product;

/*
 * Test Cases for no discount policy for any type and just to see if cart is working
 */
public class GenericTests {

	private Cart cart;
	private IProductDetails item;

	@Before
	public void setUp() {
		Customer user = new Customer(CustomerType.CUSTOMER, "John", new Date());
		cart = new Cart(user);
		item = new Product("something", 1000, ItemType.OTHER);
	}

	@Test
	public void test_emptyCartCostsZero() {
		assertEquals(0, cart.total(), 0.01);
	}

	@Test
	public void test_singleBasicItemCostsItsUnitPrice() {
		cart.add(item);
		assertEquals(item.getUnitPrice(), cart.total(), 0.01);
	}

	@Test
	public void test_multipleBasicItemsCostProportionally() {
		int howMany = 3;
		cart.add(item, howMany);
		assertEquals(howMany * item.getUnitPrice(), cart.total(), 0.01);
	}

	@Test
	public void test_separatelyAdding() {
		int howMany = 3;
		for (int i = howMany; i > 0; i--) {
			cart.add(item);
		}
		assertEquals(howMany * item.getUnitPrice(), cart.total(), 0.01);
	}
}