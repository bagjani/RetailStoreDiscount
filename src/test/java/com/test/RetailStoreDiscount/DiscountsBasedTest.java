package com.test.RetailStoreDiscount;

import static org.junit.Assert.assertEquals;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;

import com.test.PricingDiscount.IDiscount;
import com.test.PricingDiscount.OfferDiscount;
import com.test.RetailEntity.Cart;
import com.test.RetailEntity.Customer;
import com.test.RetailEntity.Customer.CustomerType;
import com.test.RetailEntity.IProductDetails;
import com.test.RetailEntity.IProductDetails.ItemType;
import com.test.RetailEntity.Product;

/*
 * Test Cases for no discount policy
 */
public class DiscountsBasedTest {

	private IProductDetails groceryItem;
	private IProductDetails groceryItem1;
	private IProductDetails otherItem;
	private Customer employee;
	private Customer affiliate;
	private Customer simpleCustomer;
	private Customer simpleCustomerWith2Years;
	private IDiscount discountPolicy;

	@Before
	public void setUp() throws ParseException {
		SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
		employee = new Customer(CustomerType.EMPLOYEE, "Akash", new Date());
		affiliate = new Customer(CustomerType.AFFILIATE, "Manish", new Date());
		simpleCustomer = new Customer(CustomerType.CUSTOMER, "Bharat", new Date());
		simpleCustomerWith2Years = new Customer(CustomerType.CUSTOMER, "Amit", formatter.parse("12/20/2016"));
		groceryItem = new Product("Pulse", 120, ItemType.GROCERY);
		groceryItem1 = new Product("Ketchup", 20, ItemType.GROCERY);
		otherItem = new Product("Radio", 200, ItemType.OTHER);
		discountPolicy = new OfferDiscount();
	}

	@Test
	public void test_employeeWithGrocery() {
		Cart cart = new Cart(employee, discountPolicy);
		cart.add(groceryItem, 4);
		// Only 5% as bill is more than 100$ .No percent based discount because of grocery item
		assertEquals(456, cart.total(), 0.01);
	}

	@Test
	public void test_employeeWithOtherItem() {
		Cart cart = new Cart(employee, discountPolicy);
		cart.add(otherItem, 4);
		/*
		 * 30% discount then 5 dollars off of each 100 dollars of total price because of
		 * other item Total 200 * 4 = 800 After 30% discount = 560 After 5% more discount
		 * off due to price over $500 = 532
		 */
		assertEquals(532, cart.total(), 0.01);

	}

	@Test
	public void test_affiliateWithGrocery() {
		Cart cart = new Cart(affiliate, discountPolicy);
		cart.add(groceryItem1, 4);
		// No discount because of grocery item
		assertEquals(80, cart.total(), 0.01);

	}

	@Test
	public void test_affiliateWithOtherItem() {
		Cart cart = new Cart(affiliate, discountPolicy);
		cart.add(otherItem, 4);
		/*
		 * 10% discount then 5 dollars off of each 100 dollars of total price because of
		 * other item Total 200 * 4 = 800 After 10% discount = 720 After 5% more discount
		 * off due to price over $700 = 684
		 */
		assertEquals(684, cart.total(), 0.01);

	}

	@Test
	public void test_simpleCustomerWithGrocery() {
		Cart cart = new Cart(simpleCustomer, discountPolicy);
		cart.add(groceryItem1, 4);
		// No discount because of grocery item
		assertEquals(80, cart.total(), 0.01);

	}

	@Test
	public void test_simpleCustomerWithOtherItem() {
		Cart cart = new Cart(simpleCustomer, discountPolicy);
		cart.add(otherItem, 4);
		/*
		 * Total 200 * 4 = 800 No percentage discount because user is a customer for
		 * less than 2 years After 40 dollars off due to price over $800 = 760
		 */
		assertEquals(760, cart.total(), 0.01);
	}

	@Test
	public void test_simpleCustomerWith2YearsWithGrocery() {
		Cart cart = new Cart(simpleCustomerWith2Years, discountPolicy);
		cart.add(groceryItem1, 4);
		// No discount because of grocery item
		assertEquals(80, cart.total(), 0.01);

	}

	@Test
	public void test_simpleCustomerWith2YearsWithOtherItem() {
		Cart cart = new Cart(simpleCustomerWith2Years, discountPolicy);
		cart.add(otherItem, 4);
		/*
		 * 5% discount then 5 dollars off of each 100 dollars of total price because of
		 * other item Total 200 * 4 = 800 After 5% discount = 760. After 5% dollars off
		 * due to price over $760 =722
		 */
		assertEquals(722, cart.total(), 0.01);

	}

	@Test
	public void test_employeeWithGroceryAndOtherItem() {
		Cart cart = new Cart(employee, discountPolicy);
		cart.add(groceryItem, 4);
		cart.add(otherItem, 4);
		/*
		 * Total (200 * 4) + (120 * 4) = 1280. No discount on grocery items = 968 still
		 * After 30% discount on 4 other items totaling 1040 After 52 dollars
		 * off due to price $1040 =988
		 */
		assertEquals(988, cart.total(), 0.01);

	}
}