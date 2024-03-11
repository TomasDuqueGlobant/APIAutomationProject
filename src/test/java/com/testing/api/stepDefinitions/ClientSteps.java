package com.testing.api.stepDefinitions;

import com.testing.api.models.Client;
import com.testing.api.requests.ClientRequest;
import com.testing.api.utils.Constants;
import com.testing.api.utils.JsonFileReader;
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
        JsonFileReader jsonFileReader = new JsonFileReader();
        Client defaultClient = jsonFileReader.getClientByJson(Constants.DEFAULT_CLIENT_FILE_PATH);

        List<Map<String, String>> clientDetails = dataTable.asMaps(String.class, String.class);
        Map<String, String> details = clientDetails.get(0);

        if (details.containsKey("name")) defaultClient.setName(details.get("name"));
        if (details.containsKey("lastName")) defaultClient.setLastName(details.get("lastName"));
        if (details.containsKey("country")) defaultClient.setCountry(details.get("country"));
        if (details.containsKey("city")) defaultClient.setCity(details.get("city"));
        if (details.containsKey("email")) defaultClient.setEmail(details.get("email"));
        if (details.containsKey("phone")) defaultClient.setPhone(details.get("phone"));


        this.client = defaultClient;
    }

    @When("I send a GET request to view all the clients")
    public void iSendAGetRequestToViewAllTheClients(){
        response = clientRequest.getClients();
        commonSteps.setResponse(response);

    }

    @When("I send a POST request to create a client")
    public void iSendAPOSTRequestToCreateAClient() {
        response = clientRequest.createClient(client);
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
        Client responseClient = response.as(Client.class);

        Assert.assertEquals( this.client.getName(), responseClient.getName());
        Assert.assertEquals( this.client.getLastName(), responseClient.getLastName());
        Assert.assertEquals( this.client.getCountry(), responseClient.getCountry());
        Assert.assertEquals( this.client.getCity(), responseClient.getCity());
        Assert.assertEquals( this.client.getEmail(), responseClient.getEmail());
        Assert.assertEquals( this.client.getPhone(), responseClient.getPhone());
    }
}
