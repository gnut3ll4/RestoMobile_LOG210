package com.gnut3ll4.restomobile.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by gnut3ll4 on 16/03/15.
 */
public class User {

    int id;
    int type;
    String prenom;
    String nom;
    String adresse;
    String error;

    @SerializedName("altadresse")
    String altAdresse;
    String telephone;

    @SerializedName("date_naissance")
    String dateNaissance;

    public int getId() {
        return id;
    }

    public int getType() {
        return type;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getNom() {
        return nom;
    }

    public String getAdresse() {
        return adresse;
    }

    public String getError() {
        return error;
    }

    public String getAltAdresse() {
        return altAdresse;
    }

    public String getTelephone() {
        return telephone;
    }

    public String getDateNaissance() {
        return dateNaissance;
    }


}
