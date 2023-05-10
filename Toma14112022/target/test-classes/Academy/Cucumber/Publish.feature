Feature: Publish an article from the Conduit website

Background:
	Given A user has landed on the Conduit landing page

@Regression
Scenario Outline: Positive path of Publishing an article
	Given A user has logged in with a username <userEmail> and a password <userPassword>
	And Verify the user has a landed on the Home page with a valid name <userName>
	When A user populates all the mandatory fields <Title> <About> <Text> <Tag> and publishes an article
	And Verify that the article has been published
	Then A user loggs out from the application
	And A headline "A place to share your knowledge." is displayed
	And A browser is closed

	
Examples:
	| userEmail							| userPassword					| userName					| Title					| About					| Text					| Tag					|
	| jovan.kovacevic@hotmail.com		| glupavipassword				| JovanK					| Title01				| About01				| Text01				| Tag01					|
	| testuser@iptiq.com				| test1234						| testuser@iptiq.com		| Title01				| About01				| Text01				| Tag01					|