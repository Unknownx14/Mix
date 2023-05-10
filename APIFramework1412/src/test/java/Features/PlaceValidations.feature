Feature: Validating Place APIs


@AddPlaceTag @RegressionTag
Scenario Outline: Verify the AddPlace functionality is working as expected
	Given The AddPlace Payload is prepared with <name> and <language> and <address>
	When A user calls the "AddPlaceAPI" with the "post" http request
	Then Verify that the new places is added successfully
	And The response has the "Status code" of "200 OK"
	And The response has the "Scope" of "APP"
	And Verify that the place_id corresponds to the <name> using the "GetPlaceAPI"
	
	
Examples:
	|name   	|language   |address   				|
	|AA House   |English   	|Maple road				|
#	|BB House   |English   	|Nelson Mandela Ave		|
	
	
@DeletePlaceTag	@RegressionTag
Scenario: Verify the DeletePlace functionality is working as expected
	Given The AddPlace functionality has been successfully completed
	When A user calls the "DeletePlaceAPI" with the DELETE http request
	Then Verify that the place is deleted successfully with the statusCode "200" OK
	And The response in the body has the "status" of "OK"
	
	
	
	