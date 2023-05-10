Feature: Error validation

Background:
	Given A user has landed on the Ecommerce landing page

@ErrorValidations
Scenario Outline: Positive path of submiting the order
	Given A user has tried to log in with an incorrect username <username> and a password <password>
	When A user clicks on the Login button
	Then Verify the message "Incorrect email or password." is displayed and a user is not logged in
	And A browser is closed
	
Examples:
	| username					| password					| 
	| unknownxwrong@gmail.com		| kojikurac123				| 