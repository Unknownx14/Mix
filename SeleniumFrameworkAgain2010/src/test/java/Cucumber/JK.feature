Feature: Purchase an order from the Ecommerce website

Background:
	Given A user has landed on the Ecommerce landing page

@JK
Scenario Outline: Positive path of submiting the order and verifying the Orders is successfully submitted
	Given A user has logged in with a username <username> and a password <password>
	When A user adds a product <productName> to the cart
	And Checkout that this product <productName> is added and submit this order
	Then Verify the message "THANKYOU FOR THE ORDER." is displayed
	And Verify that the order is listed under the Your Order table
	And A browser is closed
	
Examples:
	| username					| password					| productName					|
	| unknownxjk@gmail.com		| kojikurac123				| ZARA COAT 3					|