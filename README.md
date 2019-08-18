This is a standalone java based application which is used to calculate final discounted bill from the total bill generated. We have provided the command based approach to run different utilities. Furhter information on technologies and steps is provided below:

Java 1.7
JUnit for Unit Tests

It is implemented in java and it can be executed and tested by the running script in given sequence:
First we need to run setup.bat to build project.
Then we need to run RetailDiscount.bat to execute this project.

These files are available in the root path of the project. Once we run the scripts, we can opt the following options:
Write exit to quit
Please provide the input(s) given below...
add_customer <customer_type>, <name> <date>		//to add customer in the given format
add_product <product_name>, <price> <product_type> 	//to add product in the stock in the given 	format
show_products										// show all products available in the stock
add_prodToCart <customer_name> <productName> <quantity>	//to add products in the cart for customer
create_cart <customer_name> <isDiscount(true/false)>	//to create cart for customer
total_bill <customer_name> 								// to generate total bill after discount


Furthermore, unit test are provided that can run by executing RetailStoreDiscount.src.test.TestDiscounts and RetailStoreDiscount.src.test.TestWithoutDiscounts. To get the complete test coverage just run JUnit Tests in IDE.

In case of any errors make sure that the jars under the lib folder is included in the buld path.

General Information regarding the solution
First create the products.
Create the customer information.
Then we can create a cart which contains a number of items, and can compute the total price of its contents.

Cart items can either be actual products with a fixed unit price, or products with a pricing policy attached for any dicounts that are calculated based on items and not the overall discount policy of current cart.

As of now we have provided the basic DiscountPolicy which is used to calculate the amount based on every 100$ bill with 5% discount. We can implement more methods like that that apply different discounts based on overall cart value of contents. 