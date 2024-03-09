package com.testing.api.stepDefinitions;


import com.testing.api.models.Resource;
import com.testing.api.requests.ResourceRequest;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;

import java.util.List;

public class ResourceSteps {

    private static final Logger logger = LogManager.getLogger(ClientSteps.class);

    private ResourceRequest resourceRequest = new ResourceRequest();
    private Response response;

    private Resource resource;



    @Given("there are registered resources in the system")
    public void thereAreRegisteredResourcesInTheSystem() {
        response = resourceRequest.getResources();
        logger.info(response.jsonPath().prettify());
        Assert.assertEquals(200,response.statusCode());

        List<Resource> resourceList = resourceRequest.getResourcesEntity(response);
        if(resourceList.isEmpty()){
            response = resourceRequest.createDefaultResource();
            logger.info(response.statusCode());
            Assert.assertEquals(201,response.statusCode());
        }
    }
    @When("I send a GET request to view all the resources")
    public void iSendAGETRequestToViewAllTheResources() {
        response = resourceRequest.getResources();

    }

    @Then("the response of resources should have a status code of {int}")
    public void theResponseOfResourcesShouldHaveAStatusCodeOf(Integer statusCode) {
        Assert.assertEquals(statusCode.intValue(),response.statusCode());
    }
    @Then("validates the response with the resource list JSON schema")
    public void validatesTheResponseWithTheResourceListJSONSchema() {
        Assert.assertTrue(resourceRequest.validateSchema(response,"schemas/resourceListSchema.json"));
    }




}
