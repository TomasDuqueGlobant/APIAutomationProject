package com.testing.api.stepDefinitions;

import com.testing.api.models.Client;
import com.testing.api.requests.ClientRequest;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;

import java.util.ArrayList;
import java.util.List;

public class ClientSteps {

    private static final Logger logger = LogManager.getLogger(ClientSteps.class);

    private final ClientRequest clientRequest = new ClientRequest();
    private Response response;
    private Client client;

    @Given("there are registered clients in the system")
    public void thereAreRegisteredClientsInTheSystem(){
        response = clientRequest.getClients();
        logger.info(response.jsonPath().prettify());
        Assert.assertEquals(200,response.statusCode());

        List<Client> clientList = clientRequest.getClientsEntity(response);
        if(clientList.isEmpty()){
            response = clientRequest.createDefaultClient();
            logger.info(response.statusCode());
            Assert.assertEquals(201, response.statusCode());
        }
    }

    @When("I send a GET request to view all the clients")
    public void iSendAGetRequestToViewAllTheClients(){

    }

    @Then("the response should have a status code of {int}")
    public void theResponseShouldHaveAStatusCodeOf(Integer int1){

    }

    @Then("validates the response with client list JSON schema")
    public void validatesTheResponseWithClientListJsonSchema(){

    }

}
