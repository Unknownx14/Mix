Feature: Login with the wrong credentials

Background:
	Given A user has landed on the Ecommerce landing page

@ErrorValidation
Scenario Outline: Error message when a user tries to login with the wrong credentials
	Given A user has logged in with a wrong username <username> and a password <password>
	When A user clciks on the Login button
	Then Verify the warning message "Incorrect email or password." is displayed
	And A browser is closed
	
Examples:
	| username					| password					|
	| abcdeeee@gmail.com		| kojikurac123				|