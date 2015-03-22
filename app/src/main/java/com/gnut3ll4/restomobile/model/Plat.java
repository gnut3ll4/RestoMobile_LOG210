package com.gnut3ll4.restomobile.model;

import java.io.Serializable;

/**
 * Created by gnut3ll4 on 21/03/15.
 */
public class Plat implements Serializable {

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

    public float getPrix() {
        return prix;
    }

    String description;
    float prix;

}
