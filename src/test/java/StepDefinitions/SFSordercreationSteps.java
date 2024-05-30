package StepDefinitions;

import org.junit.Assert;

import PageObjects.FetchDetailsPage;
import PageObjects.TokenGenerationPage;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class SFSordercreationSteps {
	
	TokenGenerationPage tokengenerationpage = new TokenGenerationPage();
	FetchDetailsPage fetchdetailspage = new FetchDetailsPage();
	
	String token;
	String response_body;
	
	@Given("Generate the token through the SFS API")
	public void generate_the_token_through_the_sfs_api() {
		tokengenerationpage.generateToken();
		
		//Assert.assertNotNull(token, " token is null");
	    
	}

	@When("Extract the token from the response arrived")
	public void extract_the_token_from_the_response_arrived() {
		token = tokengenerationpage.extractToken();
		Assert.assertNotNull(token, " token is null");
	}

	@When("Pass the valid token into a variable of another API")
	public void pass_the_valid_token_into_a_variable_of_another_api() {
		fetchdetailspage.passTokenToAPI(token);
		response_body = fetchdetailspage.getResponseBody();
		
	}

	@When("Fetch the details\\(any) of the response")
	public void fetch_the_details_any_of_the_response() {
		fetchdetailspage.getDetails();
	}

}
