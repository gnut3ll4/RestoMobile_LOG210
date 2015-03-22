package com.gnut3ll4.restomobile.model;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by gnut3ll4 on 21/03/15.
 */
public class Menu implements Serializable {

    int id;
    String nom;
    ArrayList<Plat> plats;

    public int getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public ArrayList<Plat> getPlats() {
        return plats;
    }
}
