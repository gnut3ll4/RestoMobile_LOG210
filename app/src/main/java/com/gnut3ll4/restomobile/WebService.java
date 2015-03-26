package com.gnut3ll4.restomobile;

import com.gnut3ll4.restomobile.model.Restaurant;
import com.gnut3ll4.restomobile.model.TestHello;
import com.gnut3ll4.restomobile.model.User;

import java.util.ArrayList;

import retrofit.Callback;

import retrofit.http.Field;
import retrofit.http.FormUrlEncoded;
import retrofit.http.GET;
import retrofit.http.POST;

/**
 * Created by gnut3ll4 on 12/03/15.
 */
public interface WebService {

    @GET("/api.php/api")
    void helloWorld(Callback<TestHello> callback);

    @FormUrlEncoded
    @POST("/api.php/login")
    void login(@Field("username") String username, @Field("password") String password,Callback<User> callback);

    @FormUrlEncoded
    @POST("/api.php/restaurant/list")
    void listerRestaurants(@Field("username") String username, @Field("password") String password,Callback<ArrayList<Restaurant>> callback);


}
