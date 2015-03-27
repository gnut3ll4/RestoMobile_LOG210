package com.gnut3ll4.restomobile;

import android.content.Intent;
import android.database.DataSetObserver;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.gnut3ll4.restomobile.adapters.DetailsCommandeAdapter;
import com.gnut3ll4.restomobile.model.Plat;

import java.util.ArrayList;
import java.util.Map;


public class PanierActivity extends ActionBarActivity {

    private ListView listView;
    private TextView tvTotal;
    private Button commandeButton;
    private DetailsCommandeAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_panier);

        listView = (ListView) findViewById(R.id.listview_bill);
        commandeButton = (Button) findViewById(R.id.btn_commande);

        tvTotal = (TextView) findViewById(R.id.tv_total_price);


        ArrayList<Map.Entry<Plat,Integer>> listPlats = new ArrayList<>();

        float totalPrice = 0;


        for(Map.Entry<Plat,Integer> entry : ApplicationManager.panier.entrySet()){
            totalPrice += entry.getKey().getPrix()*entry.getValue();
            listPlats.add(entry);
        }

        tvTotal.setText(totalPrice+" $");



        adapter = new DetailsCommandeAdapter(PanierActivity.this,R.layout.row_panier,listPlats);
        listView.setAdapter(adapter);
        adapter.notifyDataSetChanged();


        adapter.registerDataSetObserver(new DataSetObserver() {
            @Override
            public void onChanged() {
                super.onChanged();
                refreshTotal();
            }
        });

        commandeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(PanierActivity.this, SendCommandeActivity.class);
                PanierActivity.this.startActivity(i);
            }
        });

    }


    public void refreshTotal() {
        Float totalPrice = 0.0f;
        for(Map.Entry<Plat,Integer> entry : ApplicationManager.panier.entrySet()){
            totalPrice += entry.getKey().getPrix()*entry.getValue();
        }
        tvTotal.setText(String.format("%.2f", totalPrice)+" $");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_panier, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
