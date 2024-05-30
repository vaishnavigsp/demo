package PageObjects;

import io.restassured.RestAssured;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class FetchDetailsPage {
	private String response_body2;
	private String status;
	private String message;
	private String result;
	globalVariables global = new globalVariables();
	
	public void passTokenToAPI(String token) {
		/*
		 * String url2 = ConfigReader.getProperty("baseurl") +
		 * ConfigReader.getProperty("FetchdetailsEndpoint"); String csrfToken =
		 * ConfigReader.getProperty("csrftoken"); String bodyoffetchDetails =
		 * ConfigReader.getProperty("bodyoffetchdetails");
		 * 
		 * System.out.println("Request body: " + bodyoffetchDetails);
		 * 
		 * RequestSpecification http_req2 = RestAssured.given() .header("Authorization",
		 * "Bearer " + token) .header("Content-Type", "application/json");
		 * 
		 * if(csrfToken != null && !csrfToken.isEmpty()) { http_req2.header("Cookie",
		 * "csrftoken=" + csrfToken); }
		 * 
		 * if(bodyoffetchDetails != null && !bodyoffetchDetails.isEmpty()) {
		 * http_req2.body(bodyoffetchDetails); }
		 * 
		 * Response response2 = http_req2.post(url2); response_body2 =
		 * response2.asString(); System.out.println("Response from API: " +
		 * response_body2);
		 * 
		 * if(response2.getStatusCode() == 401) { System.out.
		 * println("Authentication Failure: Please check token or authentication process."
		 * ); }
		 * 
		 * 
		 * if(response2.getStatusCode() == 400) {
		 * System.out.println("Bad Request: blaaa"); }
		 */
		
		RestAssured.baseURI = "https://store-fulfillments.qa.alo.technology";

        String payload = "{\n" +
                "  \"createdBy\": \"FDM4\",\n" +
                "  \"orderDate\": \"05/22/2024 10:42:36.920-08:00\",\n" +
                "  \"customerId\": \"1109010\",\n" +
                "  \"customerEmail\": \"sandy@gmail.com\",\n" +
                "  \"customerName\": \"Sandy Lee\",\n" +
                "  \"customerPhoneNumber\": \"12-345-678\",\n" +
                "  \"orderId\": \"QATestOrderID_101\",\n" +
                "  \"customerOrderId\": \"#9148180\",\n" +
                "  \"deliveryMethod\": \"SFS\",\n" +
                "  \"orderLineItems\": [\n" +
                "    {\n" +
                "      \"orderLineItemID\": \"1\",\n" +
                "      \"skuId\": \"W5766R040643\",\n" +
                "      \"upc\": \"884913678361\",\n" +
                "      \"orderQuantity\": 2,\n" +
                "      \"orderLineItemDiscount\": 0.0,\n" +
                "      \"orderLineItemPrice\": 128.0,\n" +
                "      \"orderLineItemTax\": 12.16\n" +
                "    }\n" +
                "  ],\n" +
                "  \"fulfillmentLocationId\": 21163081787,\n" +
                "  \"subTotalPrice\": 128.0,\n" +
                "  \"totalDiscount\": 0.0,\n" +
                "  \"totalItems\": 2,\n" +
                "  \"orderTotal\": 0,\n" +
                "  \"shipping_address\": {\n" +
                "    \"first_name\": \"Just\",\n" +
                "    \"address1\": \"9830 Wilshire Blvd\",\n" +
                "    \"address2\": \"\",\n" +
                "    \"city\": \"Beverly Hills\",\n" +
                "    \"province\": \"CA\",\n" +
                "    \"country\": \"US\",\n" +
                "    \"zip\": \"90212-1804\",\n" +
                "    \"phone\": null,\n" +
                "    \"country_code\": \"US\",\n" +
                "    \"province_code\": \"CA\"\n" +
                "  },\n" +
                "  \"shipmentType\": \"UPS GROUND\",\n" +
                "  \"timeZone\": \"America/Los_Angeles\"\n" +
                "}";
        System.out.println("global.toke"+global.token);
        Response response = given()
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer " + global.token)
                .header("Cookie", "csrftoken=VrQLHOtjJ1uYXugyHDt21tyC5V6Y5eqMcZ5s34WtTflAXUvBWoGJv48Ly1LSaGZQ; django_language=en-us")
                .body(payload)
                .when()
                .post("/bopis/cloudOrderTrigger")
                .then()
                .contentType(ContentType.JSON)
                .extract().response();

        System.out.println("Response status code: " + response.getStatusCode());
        System.out.println("Response body: " + response.getBody().asString());
		
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
