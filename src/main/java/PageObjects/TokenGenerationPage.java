package PageObjects;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import static io.restassured.RestAssured.*;

public class TokenGenerationPage {
	private String response_body;
	private String token;
	 
	globalVariables global = new globalVariables();
	

	public void generateToken() {
		 RestAssured.baseURI = ConfigReader.getProperty("baseurl");
		//String url = ConfigReader.getProperty("baseurl") + ConfigReader.getProperty("generateTokenEndpoint");
		
		String baseurl = ConfigReader.getProperty("baseurl");
        String tokenEndpoint = ConfigReader.getProperty("generateTokenEndpoint");
        String clientId = ConfigReader.getProperty("client_id");
        String clientSecret = ConfigReader.getProperty("client_secret");
        
          RestAssured.baseURI = baseurl;
//		
//				        // Make the POST request and get the response
				        Response response = given()
				            .header("Content-Type", "application/x-www-form-urlencoded")
				            .formParam("grant_type", "client_credentials")
				            .formParam("client_id", clientId)
				            .formParam("client_secret", clientSecret)
				            .post(tokenEndpoint);
				        int statusCode = response.getStatusCode();
          //response_body = response.getBody().asString();
//				        // Print the response status code and body
          if(statusCode == 200) {
        	  JsonPath jsonPath = response.jsonPath();
        	  jsonPath.getString("token");
        	  System.out.println("Access Token: " + token);
          }else {
        	  System.out.println("Failed to generate token. status code: " + statusCode) ;
          }
				        //global.token = extractToken();
				        System.out.println("Response status code: " + response.getStatusCode());
				        System.out.println("Response body: " + response.getBody().asString());
				        System.out.println("extract token..: " + global.token);
		
				        
	}

	public String extractToken() {
//		
        //System.out.println("Extracted Token: " + token);
//		jsonPath.getString("token");
//		System.out.println("..response_body.." + response_body + " one");
		//token = response_body.substring(response_body.indexOf(":") + 2, response_body.lastIndexOf("\""));
		return token;
		
	}

}
