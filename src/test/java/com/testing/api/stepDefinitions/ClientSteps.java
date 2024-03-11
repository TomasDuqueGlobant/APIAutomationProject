package com.testing.api.stepDefinitions;

import com.testing.api.models.Client;
import com.testing.api.requests.ClientRequest;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import org.apache.http.client.HttpClient;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;


import java.util.List;
import java.util.Map;

public class ClientSteps {

    private static final Logger logger = LogManager.getLogger(ClientSteps.class);

    private final ClientRequest clientRequest = new ClientRequest();

    private CommonSteps commonSteps = new CommonSteps();
    private Response response;
    private Client client;

    public ClientSteps(CommonSteps commonSteps) {
        this.commonSteps = commonSteps;
    }

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
        commonSteps.setResponse(response);

    }

    @Given("I have a client with the following details:")
    public void iHaveAClientWithTheFollowingDetails(io.cucumber.datatable.DataTable dataTable) {
        List<Map<String, String>> clientDetails = dataTable.asMaps(String.class, String.class);
        Map<String, String> details = clientDetails.get(0);

        this.client = new Client(
                details.get("name"),
                details.get("lastName"),
                details.get("country"),
                details.get("city"),
                details.get("email"),
                details.get("phone"),
                details.get("id")
        );
    }

    @When("I send a GET request to view all the clients")
    public void iSendAGetRequestToViewAllTheClients(){
        response = clientRequest.getClients();
        commonSteps.setResponse(response);

    }

    @When("I send a POST request to create a client")
    public void iSendAPOSTRequestToCreateAClient() {
        response = clientRequest.createDefaultClient();
        commonSteps.setResponse(response);
    }



    @Then("validates the response with client list JSON schema")
    public void validatesTheResponseWithClientListJsonSchema(){
        Assert.assertTrue(clientRequest.validateSchema(response,"schemas/clientListSchema.json"));
    }
    @Then("validates the response with client JSON schema")
    public void validatesTheResponseWithClientJsonSchema(){
        Assert.assertTrue(clientRequest.validateSchema(response,"schemas/clientSchema.json"));
    }

    @Then("the response should include the details of the created client")
    public void theResponseShouldIncludeTheDetailsOfTheCreatedClient() {
        Assert.assertEquals(200,response.getStatusCode());

    }
}
