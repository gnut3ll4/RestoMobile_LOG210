package com.gnut3ll4.restomobile.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;


import com.gnut3ll4.restomobile.R;
import com.gnut3ll4.restomobile.model.Restaurant;

import java.util.List;

/**
 * Created by gnut3ll4 on 22/03/15.
 */
public class RestaurantsAdapter extends ArrayAdapter<Restaurant> {

    private final LayoutInflater inflater;

    public RestaurantsAdapter(Context context, int resource, List<Restaurant> objects) {
        super(context, resource, objects);
        this.context = context;
        this.inflater = LayoutInflater.from(context);
    }


    private Context context;

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        ViewHolder holder;
        if (view != null) {
            holder = (ViewHolder) view.getTag();
        } else {
            view = inflater.inflate(R.layout.row_list_restaurants, parent, false);
            holder = new ViewHolder();
            holder.tvRestaurant = (TextView) view.findViewById(R.id.tv_row_restaurant_nom);

            view.setTag(holder);
        }

        Restaurant item = getItem(position);

        holder.tvRestaurant.setText(item.getNom());

        return view;
    }

    static class ViewHolder {
        TextView tvRestaurant;


    }

}
