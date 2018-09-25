package com.qa.AdvancedHTTPRequests;

import cucumber.api.PendingException;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.when;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;

import org.json.simple.JSONObject;
import static org.hamcrest.Matchers.*;
import static org.hamcrest.Matchers.equalTo;

public class StepDefinitionRequests {

	private Response response; 
	private RequestSpecification request; 
	private ValidatableResponse json; 
	
	JSONObject jsonObj = new JSONObject();
	
	//POST
	@Given("^I want to post a hotel that exists$")
	public void i_want_to_post_a_hotel_that_exists() throws Throwable {
	   //request = given().contentType(ContentType.JSON);
		
		RestAssured.baseURI = Constants.URL;
		
	   request = RestAssured.given();
	   request.header("Content-Type", "application/json");
	}

	@When("^I include its name, description, city and rating$")
	public void i_include_its_name_description_city_and_rating() throws Throwable {
		
		jsonObj.put("name", "JOHN");
		jsonObj.put("description", "modern, exciting, upcoming hotel suitable for families");
		jsonObj.put("city", "Cardiff");
		jsonObj.put("rating", "2");
		request.body(jsonObj.toString());
		response = request.when().post("/"); 
		
	}

	@Then("^It's posted to the website$")
	public void it_s_posted_to_the_website() {
		
		json = response.then().statusCode(201); 
	}
	
	//GET
	@When("^I get the id$")
	public void i_get_the_id() 
	{
		response = request.when().get(Constants.URL + "/2");
	}

	@Then("^The location is equal to London$")
	public void the_location_is_equal_to_London() 
	{
		json = response.then().statusCode(200);
	}
	
	
	//PUT
	@Given("^A hotel in Cardiff called JOHN exists$")
	public void a_hotel_in_Cardiff_called_JOHN_exists() 
	{
	    //request = given().contentType(ContentType.JSON); 
		RestAssured.baseURI = Constants.URL;
		
		request = RestAssured.given();
		request.header("Content-Type", "application/json");
		
	}

	@When("^I change the rating to (\\d+)$")
	public void i_change_the_rating_to(int arg1) 
	{
	    //response = request.when().get(Constants.URL + "/4");
		jsonObj.put("id", 5);
		jsonObj.put("name", "SheepyLeak");
		jsonObj.put("description", "small little hotel in the outskirts of cardiff");
		jsonObj.put("rating", 4);
		jsonObj.put("city", "Cardiff");
		request.body(jsonObj.toString());
	    response = request.when().put("/5");
	}

	@Then("^The rating is now equal to (\\d+)$")
	public void the_rating_is_now_equal_to(int arg1) 
	{
	    json = response.then().statusCode(204);
	}

	//DELETE
	@Given("^A Hotel exists in Paris$")
	public void a_Hotel_exists_in_Paris() 
	{
		RestAssured.baseURI = Constants.URL;
		
		request = RestAssured.given();
		request.header("Content-Type", "application/json");
	}

	@When("^I retrieve the hotel$")
	public void i_retrieve_the_hotel() throws Throwable {
	    response = request.when().delete("/3");
	}


	@Then("^the status code is not equal to (\\d+)$")
	public void the_status_code_is_not_equal_to(int arg1) 
	{
	    json = response.then().statusCode(404);
	}
	
}
