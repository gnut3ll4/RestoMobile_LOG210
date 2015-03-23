package com.gnut3ll4.restomobile.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;


import com.gnut3ll4.restomobile.ApplicationManager;
import com.gnut3ll4.restomobile.R;
import com.gnut3ll4.restomobile.model.Plat;
import com.gnut3ll4.restomobile.model.Restaurant;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by gnut3ll4 on 22/03/15.
 */
public class DetailsCommandeAdapter extends ArrayAdapter<Map.Entry<Plat,Integer>> {

    private final LayoutInflater inflater;
    private Context context;

    public DetailsCommandeAdapter(Context context, int resource, ArrayList<Map.Entry<Plat, Integer>> objects) {
        super(context, resource, objects);
        this.context = context;
        this.inflater = LayoutInflater.from(context);
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        ViewHolder holder;
        if (view != null) {
            holder = (ViewHolder) view.getTag();
        } else {
            view = inflater.inflate(R.layout.row_panier, parent, false);
            holder = new ViewHolder();
            holder.tvNamePlat = (TextView) view.findViewById(R.id.tv_name_plat);
            holder.tvNumber = (TextView) view.findViewById(R.id.tv_number);
            holder.tvUnitPrice = (TextView) view.findViewById(R.id.tv_unit_price);

            holder.buttonMinus = (Button) view.findViewById(R.id.btn_minus);
            holder.buttonPlus = (Button) view.findViewById(R.id.btn_plus);

            view.setTag(holder);
        }

        final Map.Entry<Plat,Integer> item = getItem(position);

        final Plat plat = item.getKey();
        final Integer number = item.getValue();


        holder.tvNamePlat.setText(plat.getNom());
        holder.tvUnitPrice.setText(plat.getPrix()+" $");
        holder.tvNumber.setText(""+number);


        holder.buttonMinus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(number-1==0) {
                    ApplicationManager.panier.remove(plat);
                    remove(item);
                } else {
                    ApplicationManager.panier.put(plat,number-1);
                }
                notifyDataSetChanged();
            }
        });

        holder.buttonPlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ApplicationManager.panier.put(plat,number+1);
                notifyDataSetChanged();
            }
        });

        return view;
    }

    static class ViewHolder {
        TextView tvNamePlat;
        TextView tvUnitPrice;
        TextView tvNumber;
        Button buttonMinus;
        Button buttonPlus;


    }

}

