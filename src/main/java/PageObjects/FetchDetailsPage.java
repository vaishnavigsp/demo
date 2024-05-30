package PageObjects;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class FetchDetailsPage {
	private String response_body2;
	private String status;
	private String message;
	private String result;
	
	public void passTokenToAPI(String token) {
		String url2 = ConfigReader.getProperty("baseurl") + ConfigReader.getProperty("FetchdetailsEndpoint");
		String csrfToken = ConfigReader.getProperty("csrftoken");
		String bodyoffetchDetails = ConfigReader.getProperty("bodyoffetchdetails");
		
		System.out.println("Request body: " + bodyoffetchDetails);
		
		RequestSpecification http_req2 = RestAssured.given()
				.header("Authorization", "Bearer " + token)
				.header("Content-Type", "application/json");
		
		if(csrfToken != null && !csrfToken.isEmpty()) {
			http_req2.header("Cookie", "csrftoken=" + csrfToken);
		}
		
		if(bodyoffetchDetails != null && !bodyoffetchDetails.isEmpty()) {
			http_req2.body(bodyoffetchDetails);
		}
				
		Response response2 = http_req2.post(url2);
		response_body2 = response2.asString();
		System.out.println("Response from API: " + response_body2);
		
		if(response2.getStatusCode() == 401) {
			System.out.println("Authentication Failure: Please check token or authentication process.");
		}
        

		if(response2.getStatusCode() == 400) {
			System.out.println("Bad Request: blaaa");
		}

}
	public void getDetails() {
		JsonPath jsonPath2 = new JsonPath(response_body2);
		 status =  jsonPath2.getString("detail.status");
		 message =  jsonPath2.getString("detail.message");
		 result =  jsonPath2.getString("detail.result");
		 System.out.println("....response_body2..." + response_body2 + " two");
		
	}
	public String getResponseBody() {
		return response_body2;
	}
	}
