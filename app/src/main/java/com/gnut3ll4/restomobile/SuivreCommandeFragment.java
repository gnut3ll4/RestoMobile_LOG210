package com.gnut3ll4.restomobile;

import android.content.Intent;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import com.gnut3ll4.restomobile.adapters.CommandeAdapter;
import com.gnut3ll4.restomobile.adapters.RestaurantsAdapter;
import com.gnut3ll4.restomobile.model.Commande;
import com.gnut3ll4.restomobile.model.Restaurant;


import java.io.Serializable;
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


    private ListView listeViewCommandes;
    private CommandeAdapter adapter;
    private SwipeRefreshLayout swipeRefreshLayout;
    private WebService service;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_suivre_commande, container, false);

        listeViewCommandes = (ListView) v.findViewById(R.id.listview_commande);
        swipeRefreshLayout = (SwipeRefreshLayout) v.findViewById(R.id.swipe_refresh_layout);


        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint(getString(R.string.server))
                .build();

        service = restAdapter.create(WebService.class);

        String username = ApplicationManager.userCredentials.getUsername();
        String password = ApplicationManager.userCredentials.getPassword();

        service.listerCommandes(username,password,this);

        swipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                String username = ApplicationManager.userCredentials.getUsername();
                String password = ApplicationManager.userCredentials.getPassword();
                service.listerCommandes(username,password,SuivreCommandeFragment.this);
            }
        });
        return v;
    }


    @Override
    public void success(Object o, Response response) {
        swipeRefreshLayout.setRefreshing(false);
        final ArrayList<Commande> commandes = (ArrayList<Commande>) o;


        adapter = new CommandeAdapter(getActivity(),R.layout.row_etat_commande,commandes);
        listeViewCommandes.setAdapter(adapter);
        adapter.notifyDataSetChanged();



    }

    @Override
    public void failure(RetrofitError error) {

    }
}
