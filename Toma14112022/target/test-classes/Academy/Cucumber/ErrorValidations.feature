Feature: Validate all the Errors related to the Login

Background:
	Given A user has landed on the Conduit landing page

@ErrorValid
Scenario Outline: Error message is displayed when a wrong username and/or password is used
	Given A user has logged in with a wrong username <userEmail> and/or password <userPassword>
	When Verify the user has not landed on the Home page
	Then A message "email or password is invalid" is displayed
	And A browser is closed

	
Examples:
	| userEmail							| userPassword					| userName					|
	| jovan.kovacevic@hotmail.com		| glupavipassword01				| JovanK					|
	
	
	
	
	@ErrorValid
Scenario Outline: Wrong username is displayed
	Given A user has logged in with a correct username <userEmail> and password <userPassword>
	Then Verify that the user's <userName> is not as expected
	And A browser is closed

	
Examples:
	| userEmail							| userPassword					| userName					|
	| jovan.kovacevic@hotmail.com		| glupavipassword				| JovanK					|