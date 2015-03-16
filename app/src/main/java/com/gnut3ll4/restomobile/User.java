package com.gnut3ll4.restomobile;

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


}
