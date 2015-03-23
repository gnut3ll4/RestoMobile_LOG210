package com.gnut3ll4.restomobile;

import android.app.Activity;
import android.app.Application;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.gnut3ll4.restomobile.model.Plat;
import com.gnut3ll4.restomobile.model.User;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by gnut3ll4 on 16/03/15.
 */
public class ApplicationManager extends Application {


    public static UserCredentials userCredentials;
    public static HashMap<Plat,Integer> panier;
    public static User user;

    @Override
    public void onCreate() {
        super.onCreate();
        SecurePreferences securePreferences = new SecurePreferences(this);
        String u = securePreferences.getString(UserCredentials.USERNAME, "");
        String p = securePreferences.getString(UserCredentials.PASSWORD, "");

        if (u.length() > 0 && p.length() > 0) {
            userCredentials = new UserCredentials(u, p);
        }


        String id = securePreferences.getString("UserId", "");
        String type = securePreferences.getString("UserType", "");
        String prenom = securePreferences.getString("UserPrenom", "");
        String nom = securePreferences.getString("UserNom", "");
        String adresse = securePreferences.getString("UserAdresse", "");
        String altAdresse =  securePreferences.getString("UserAltAdresse", "");
        String telephone = securePreferences.getString("UserTelephone", "");
        String dateNaissance = securePreferences.getString("UserDateNaiss", "");

        if(id.length()>0 && type.length()>0) {
            user = new User(Integer.valueOf(id),Integer.valueOf(type),prenom,nom,adresse, altAdresse, telephone, dateNaissance);
        }

        panier = new HashMap<>();

    }

    public static void deconnexion(final Activity activity) {


        final SharedPreferences.Editor editor = PreferenceManager.getDefaultSharedPreferences(activity).edit();
        editor.clear();
        editor.commit();

        // Enlever le profil de la DB SQLite


        ApplicationManager.userCredentials = null;
        Intent intent = new Intent(activity, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        activity.startActivity(intent);
        new Thread(new Runnable() {

            @Override
            public void run() {
                activity.finish();
            }
        }).start();

    }
}
