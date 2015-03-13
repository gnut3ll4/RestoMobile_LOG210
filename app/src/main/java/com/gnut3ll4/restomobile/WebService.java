package com.gnut3ll4.restomobile;

import retrofit.Callback;
import retrofit.client.Response;
import retrofit.http.GET;

/**
 * Created by gnut3ll4 on 12/03/15.
 */
public interface WebService {
    @GET("/log210/LOG210/api.php/api")
    void helloWorld(Callback<TestHello> callback);

}
