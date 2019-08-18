package com.test.RetailStore;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class RetailStoreMainApp {

	public static void main(String[] args) {

		InputParser inputParser = new InputParser();
		
		System.out.println("Write exit to quit");
    	System.out.println("Please provide the input(s) given below...");
    	System.out.println("add_customer <customer_type>, <name> <date>");
    	System.out.println("add_product <product_name>, <price> <product_type>");
    	System.out.println("show_products");
    	System.out.println("add_prodToCart <customer_name> <productName> <quantity>");
    	System.out.println("create_cart <customer_name> <isDiscount(true/false)>");
    	System.out.println("total_bill <customer_name>");
		// Interactive command-line input/output
		// Run an infinite loop
		for (;;) {
			try {
				BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
				String inputString = bufferRead.readLine();
				if (inputString.equalsIgnoreCase("exit")) {
					break;
				} else if ((inputString == null) || (inputString.isEmpty())) {
					// Do nothing
				} else {
					inputParser.parseTextInput(inputString.trim());
				}
			} catch (IOException e) {
				System.out.println("Oops! Error in reading the input from console.");
				e.printStackTrace();
			}
		}
	}
}
