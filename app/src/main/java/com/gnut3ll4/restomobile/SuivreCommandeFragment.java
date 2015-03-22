package com.gnut3ll4.restomobile;

import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.gnut3ll4.restomobile.model.Restaurant;


import java.util.ArrayList;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class SuivreCommandeFragment extends Fragment implements Callback {
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    public static SuivreCommandeFragment newInstance(String param1, String param2) {
        SuivreCommandeFragment fragment = new SuivreCommandeFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public SuivreCommandeFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }


    private TextView tvTest;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_suivre_commande, container, false);


        tvTest = (TextView) v.findViewById(R.id.tv_test);


        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint(getString(R.string.server))
                .build();

        WebService service = restAdapter.create(WebService.class);

        String username = ApplicationManager.userCredentials.getUsername();
        String password = ApplicationManager.userCredentials.getPassword();



        service.listerRestaurants(username,password,this);




        return v;
    }


    @Override
    public void success(Object o, Response response) {
        ArrayList<Restaurant> restaurants = (ArrayList<Restaurant>) o;

        tvTest.setText(restaurants.get(1).getMenus().get(0).getPlats().get(0).getNom());


    }

    @Override
    public void failure(RetrofitError error) {
        tvTest.setText("error");
    }
}
