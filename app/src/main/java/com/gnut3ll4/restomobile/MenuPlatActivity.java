package com.gnut3ll4.restomobile;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.ExpandableListView;
import android.widget.Toast;

import com.gnut3ll4.restomobile.adapters.ExpandableListAdapter;
import com.gnut3ll4.restomobile.model.Menu;
import com.gnut3ll4.restomobile.model.Plat;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by gnut3ll4 on 21/03/15.
 */
public class MenuPlatActivity extends ActionBarActivity {


    private ExpandableListView listviewMenuPlats;
    private ExpandableListAdapter adapter;
    private ArrayList<Menu> listeMenus;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_plat);



        listviewMenuPlats = (ExpandableListView) findViewById(R.id.explistview_menu_plat);

        listeMenus = (ArrayList<Menu>) getIntent().getSerializableExtra("listMenu");


        List<String> listDataHeader = new ArrayList<>();
        HashMap<String, List<Plat>> listChildData = new HashMap<>();

        for(Menu menu : listeMenus) {
            listDataHeader.add(menu.getNom());
            listChildData.put(menu.getNom(),menu.getPlats());
        }

        adapter = new ExpandableListAdapter(this,listDataHeader,listChildData);
        listviewMenuPlats.setAdapter(adapter);
        adapter.notifyDataSetChanged();



        listviewMenuPlats.setOnChildClickListener(new ExpandableListView.OnChildClickListener() {

            @Override
            public boolean onChildClick(ExpandableListView parent, View v,int groupPosition, int childPosition, long id) {

                Plat plat = (Plat) adapter.getChild(groupPosition, childPosition);

                Integer currentNumber = ApplicationManager.panier.get(plat);

                ApplicationManager.panier.put(plat,currentNumber == null ? 1 : currentNumber+1);

                Toast.makeText(MenuPlatActivity.this,plat.getNom()+" a été ajouté au panier",Toast.LENGTH_SHORT).show();

                return true;
            }
        });


    }


    @Override
    public boolean onCreateOptionsMenu(android.view.Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        if (id == R.id.action_disconnect) {
            ApplicationManager.deconnexion(this);
        }

        if(id == R.id.action_panier){
            Intent i = new Intent(MenuPlatActivity.this, PanierActivity.class);
            MenuPlatActivity.this.startActivity(i);
        }

        return super.onOptionsItemSelected(item);


    }


}
