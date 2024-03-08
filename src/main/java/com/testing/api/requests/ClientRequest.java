package com.testing.api.requests;

import com.testing.api.models.Client;
import com.testing.api.utils.Constants;
import com.testing.api.utils.JsonFileReader;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class ClientRequest extends BaseRequest{

    private String endpoint;

    public Response getClients(){
        endpoint = "";
        return requestGet(endpoint,createBaseHeaders());
    }

    public Response createClient(Client client){
        endpoint = "";
        return requestPost(endpoint,createBaseHeaders(),client);
    }

    public Response createDefaultClient(){
        JsonFileReader jsonFileReader = new JsonFileReader();
        return this.createClient(jsonFileReader.getClientByJson(Constants.DEFAULT_CLIENT_FILE_PATH));
    }


    public List<Client> getClientsEntity(@NotNull Response response){
        JsonPath jsonPath = response.jsonPath();
        return jsonPath.getList("",Client.class);
    }

    public boolean validateSchema(Response response, String schemaPath){
        try{
            response.then().assertThat().body(JsonSchemaValidator.matchesJsonSchemaInClasspath(schemaPath));
            return true;
        }catch(AssertionError e){
            return false;
        }
    }

}
