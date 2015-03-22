package com.gnut3ll4.restomobile;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ExpandableListView;

import com.gnut3ll4.restomobile.adapters.ExpandableListAdapter;
import com.gnut3ll4.restomobile.model.Menu;
import com.gnut3ll4.restomobile.model.Plat;

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

    }


}
