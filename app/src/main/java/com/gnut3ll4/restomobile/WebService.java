package com.gnut3ll4.restomobile;

import retrofit.Callback;
import retrofit.client.Response;
import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.GET;
import retrofit.http.POST;

/**
 * Created by gnut3ll4 on 12/03/15.
 */
public interface WebService {

    @GET("/log210/LOG210/api.php/api")
    void helloWorld(Callback<TestHello> callback);

    @FormUrlEncoded
    @POST("/log210/LOG210/api.php/login")
    void login(@Field("username") String username, @Field("password") String password,Callback<User> callback);

}
