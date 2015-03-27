package com.gnut3ll4.restomobile.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.gnut3ll4.restomobile.R;
import com.gnut3ll4.restomobile.model.Commande;
import com.gnut3ll4.restomobile.model.Restaurant;

import java.util.List;

/**
 * Created by gnut3ll4 on 27/03/15.
 */
public class CommandeAdapter extends ArrayAdapter<Commande> {

    private final LayoutInflater inflater;

    public CommandeAdapter(Context context, int resource, List<Commande> objects) {
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
            view = inflater.inflate(R.layout.row_etat_commande, parent, false);
            holder = new ViewHolder();
            holder.tvDate = (TextView) view.findViewById(R.id.tv_date);
            holder.tvEtat = (TextView) view.findViewById(R.id.tv_etat);
            holder.tvId = (TextView) view.findViewById(R.id.tv_id);

            view.setTag(holder);
        }

        Commande item = getItem(position);

        holder.tvId.setText(""+item.getId());
        holder.tvDate.setText(item.getDate());

        String etat = "";

        switch (item.getEtat()) {
            case 0:
                etat = "En préparation";
                break;
            case 1:
                etat = "En livraison";
                break;
            default:
                etat = "Livrée";
                break;
        }
        holder.tvEtat.setText(etat);

        return view;
    }

    static class ViewHolder {
        TextView tvId;
        TextView tvDate;
        TextView tvEtat;
    }

}
