package com.gnut3ll4.restomobile.model;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

/**
 * Created by gnut3ll4 on 21/03/15.
 */
public class Restaurant {

    int id;
    String nom;

    public int getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public String getDescription() {
        return description;
    }

    public ArrayList<Menu> getMenus() {
        return menus;
    }

    String description;
    ArrayList<Menu> menus;

}


