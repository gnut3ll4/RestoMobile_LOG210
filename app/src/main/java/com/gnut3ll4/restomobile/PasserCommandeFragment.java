package com.gnut3ll4.restomobile;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.gnut3ll4.restomobile.adapters.RestaurantsAdapter;
import com.gnut3ll4.restomobile.model.Restaurant;

import java.io.Serializable;
import java.util.ArrayList;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by gnut3ll4 on 21/03/15.
 */
public class PasserCommandeFragment extends Fragment implements Callback {

    private ListView listviewRestaurants;
    private ProgressBar progressBar;
    private RestaurantsAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {

        View v = inflater.inflate(R.layout.fragment_passer_commande, container, false);

        listviewRestaurants = (ListView) v.findViewById(R.id.listview_restaurants);

        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint(getString(R.string.server))
                .build();

        WebService service = restAdapter.create(WebService.class);

        String username = ApplicationManager.userCredentials.getUsername();
        String password = ApplicationManager.userCredentials.getPassword();


        progressBar = (ProgressBar) v.findViewById(R.id.progressbar);
        progressBar.setIndeterminate(true);
        progressBar.setVisibility(View.VISIBLE);

        

        service.listerRestaurants(username, password, this);


        return v;
    }



    @Override
    public void success(Object o, final Response response) {

        progressBar.setVisibility(View.GONE);
            final ArrayList<Restaurant> restaurants = (ArrayList<Restaurant>) o;

        if(restaurants.size()>0) {
            adapter = new RestaurantsAdapter(getActivity(), R.layout.row_list_restaurants, restaurants);
            listviewRestaurants.setAdapter(adapter);
            adapter.notifyDataSetChanged();

            listviewRestaurants.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                    Restaurant restaurant = (Restaurant) parent.getItemAtPosition(position);


                    Intent i = new Intent(getActivity(), MenuPlatActivity.class);

                    i.putExtra("listMenu", (Serializable) restaurant.getMenus());

                    getActivity().startActivity(i);

//                Toast.makeText(getActivity(),""+restaurant.getNom(),Toast.LENGTH_SHORT).show();

                }
            });
        }
    }

    @Override
    public void failure(RetrofitError error) {

    }
}
