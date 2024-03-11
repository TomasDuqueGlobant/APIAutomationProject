package com.testing.api.stepDefinitions;

import io.cucumber.java.en.Then;
import io.restassured.response.Response;
import org.junit.Assert;

public class CommonSteps {

    private Response response;


    public void setResponse(Response response) {
        this.response = response;
    }

    @Then("the response should have a status code of {int}")
    public void theResponseShouldHaveAStatusCodeOf(Integer statusCode){
        Assert.assertEquals(statusCode.intValue(),response.statusCode());
    }
}
