package com.gnut3ll4.restomobile;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by gnut3ll4 on 21/03/15.
 */
public class PasserCommandeFragment extends Fragment implements Callback {


    private TextView tvTest;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_passer_commande, container, false);




        tvTest = (TextView) v.findViewById(R.id.tv_test);


        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint(getString(R.string.server))
                .build();

        WebService service = restAdapter.create(WebService.class);


        service.helloWorld(this);


        return v;
    }



    @Override
    public void success(Object o, Response response) {

        TestHello testHello = (TestHello) o;

        tvTest.setText(testHello.hello);
    }

    @Override
    public void failure(RetrofitError error) {
        tvTest.setText(error.getMessage());
    }
}
