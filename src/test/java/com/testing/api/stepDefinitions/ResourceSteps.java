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
    private CommonSteps commonSteps = new CommonSteps();
    private Response response;

    private Resource resource;

    public ResourceSteps(CommonSteps commonSteps){
        this.commonSteps = commonSteps;
    }

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
        commonSteps.setResponse(response);
    }

    @Given("I retrieve the details of the latest resource")
    public void iRetrieveTheDetailsOfTheLatestResource() {
        response = resourceRequest.getResources();
        List<Resource> resourceList = resourceRequest.getResourcesEntity(response);
        if (!resourceList.isEmpty()) {
            resource = resourceList.get(resourceList.size() - 1);
        } else {
            throw new IllegalStateException("No resources found.");
        }
        commonSteps.setResponse(response);

    }

    @When("I send a GET request to view all the resources")
    public void iSendAGETRequestToViewAllTheResources() {
        response = resourceRequest.getResources();
        commonSteps.setResponse(response);
    }
    @When("I send a PUT request to update the latest resource")
    public void iSendAPUTRequestToUpdateTheLatestResource(String docString) {
        if (resource == null || resource.getId() == null) {
            throw new IllegalStateException("No last resource available for update.");
        }
        response = resourceRequest.updateResource(resource.getId(), docString);
        commonSteps.setResponse(response);
    }

    @Then("validates the response with the resource list JSON schema")
    public void validatesTheResponseWithTheResourceListJSONSchema() {
        Assert.assertTrue(resourceRequest.validateSchema(response,"schemas/resourceListSchema.json"));
    }
    @Then("the response should have the following details:")
    public void theResponseShouldHaveTheFollowingDetails() {

    }
}
