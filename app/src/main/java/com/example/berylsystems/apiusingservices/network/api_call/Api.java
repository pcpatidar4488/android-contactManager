package com.example.berylsystems.apiusingservices.network.api_call;

import com.example.berylsystems.apiusingservices.network.pojo.response.signin.ResponseSignIn;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by BerylSystems on 17-Mar-18.
 */

public interface Api {
    @POST("api_test/")
    @FormUrlEncoded
    Call<ResponseSignIn> postApi(@Field("username") String username, @Field("password") String password);
}
