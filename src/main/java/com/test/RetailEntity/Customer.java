package com.test.RetailEntity;

import java.util.Calendar;
import java.util.Date;

public class Customer {
	
	public enum CustomerType {
		
		AFFILIATE(10),EMPLOYEE(30),CUSTOMER(5);
		
		private int discountPercentage;
		
		CustomerType(int discountPercentage) {
			this.discountPercentage = discountPercentage;
		}
		
		public int getDiscountPercentage() {
			return discountPercentage;
		}

	}
	
	private static final int CUSTOMER_LOYALTY_TIME_PERIOD_IN_YRS = -2;

	private CustomerType customer;

	private String customerName;

	private Date registrationDate;

	public Customer(CustomerType CustomerType, String customerName, Date registrationDate) {
		super();
		this.customer = CustomerType;
		this.customerName = customerName;
		this.registrationDate = registrationDate;
	}

	public CustomerType getCustomerType() {
		return customer;
	}

	public String getCustomerName() {
		return customerName;
	}

	public Date getRegistrationDate() {
		return registrationDate;
	}

	public boolean isAvailableForLoyaltyDiscount() {
		Calendar cal = Calendar.getInstance();
		cal.add(Calendar.YEAR, CUSTOMER_LOYALTY_TIME_PERIOD_IN_YRS);
		Date dateToCompare = cal.getTime();
		return registrationDate.before(dateToCompare) && (CustomerType.CUSTOMER == customer);
	}

}
