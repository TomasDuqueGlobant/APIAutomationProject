package com.testing.api.requests;

import com.testing.api.models.Resource;
import com.testing.api.utils.Constants;
import com.testing.api.utils.JsonFileReader;
import io.restassured.module.jsv.JsonSchemaValidator;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.jetbrains.annotations.NotNull;

import java.util.List;

public class ResourceRequest extends BaseRequest{

    private String endpoint;

    public Response getResources(){
        endpoint = "https://63b6dfe11907f863aa04ff81.mockapi.io/api/v1/resources";
        return requestGet(endpoint,createBaseHeaders());
    }

    public Response createResource(Resource resource){
        endpoint = "https://63b6dfe11907f863aa04ff81.mockapi.io/api/v1/resources";
        return requestPost(endpoint,createBaseHeaders(),resource);
    }

    public Response createDefaultResource(){
        JsonFileReader jsonFileReader = new JsonFileReader();
        return this.createResource(jsonFileReader.getResourceByJson(Constants.DEFAULT_RESOURCE_FILE_PATH));
    }

    public List<Resource> getResourcesEntity(@NotNull Response response){
        JsonPath jsonPath = response.jsonPath();
        return jsonPath.getList("",Resource.class);
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
