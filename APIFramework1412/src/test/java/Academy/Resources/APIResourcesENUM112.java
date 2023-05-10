package Academy.Resources;

public enum APIResourcesENUM112 {

	
	AddPlaceAPI("maps/api/place/add/json"),
	GetPlaceAPI("maps/api/place/get/json"),
	DeletePlaceAPI("maps/api/place/delete/json");
	
	private String resource;
	
	//Create a Constructor that accepts the same variable as the methods above
	APIResourcesENUM112(String resource)
	{
		this.resource = resource;
	}
	
	public String getResource()
	{
		return resource;
	}
	
}
