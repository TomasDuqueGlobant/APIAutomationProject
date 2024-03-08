package com.testing.api.utils;

import com.google.gson.Gson;
import com.testing.api.models.Client;

import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;

public class JsonFileReader {

    public Client getClientByJson(String jsonFileName){
        Client client = new Client();
        try(Reader reader = new FileReader(jsonFileName)){
            Gson gson = new Gson();
            client = gson.fromJson(reader,Client.class);
        }catch (IOException e){
            e.printStackTrace();
        }
        return client;
    }
}
