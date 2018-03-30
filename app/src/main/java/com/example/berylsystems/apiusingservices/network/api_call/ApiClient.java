package com.example.berylsystems.apiusingservices.network.api_call;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by BerylSystems on 17-Mar-18.
 */

public class ApiClient {

    public static String  BASE_URL = "http://brajendra.pythonanywhere.com/myapp/";

    public static Api getClient(){
        Retrofit retrofit=null;
        if (retrofit==null){
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

        }
        Api service = retrofit.create(Api.class);
        return service;
    }

}
