package com.testing.api.requests;


import com.testing.api.utils.Constants;
import io.restassured.RestAssured;
import io.restassured.response.Response;

import java.util.HashMap;
import java.util.Map;

public class BaseRequest {

    protected Response requestGet(String endpoint, Map<String, ?> headers){
        return RestAssured.given().contentType(Constants.VALUE_CONTENT_TYPE).headers(headers).get(endpoint);
    }

    protected Response requestPost(String endpoint, Map<String, ?> headers, Object body){
        return RestAssured.given().contentType(Constants.VALUE_CONTENT_TYPE).headers(headers).body(body).when().post(endpoint);
    }

//    protected Response requestPut(String endpoint, Map<String, ?> headers, Object body){
//        return RestAssured.given().contentType(Constants.VALUE_CONTENT_TYPE).headers(headers).body(body).when().put(endpoint);
//    }

    public Response requestPut(String endpoint, Map<String, String> headers, String requestBody) {
        return RestAssured.given()
                .headers(headers)
                .body(requestBody)
                .when()
                .put(endpoint)
                .then()
                .extract()
                .response();
    }

    protected Response requestDelete(String endpoint, Map<String, ?> headers){
        return RestAssured.given().contentType(Constants.VALUE_CONTENT_TYPE).headers(headers).when().delete(endpoint);
    }

    protected Map<String, String> createBaseHeaders(){
        Map<String, String> headers = new HashMap<>();
        headers.put(Constants.CONTENT_TYPE, Constants.VALUE_CONTENT_TYPE);
        return headers;
    }
}
