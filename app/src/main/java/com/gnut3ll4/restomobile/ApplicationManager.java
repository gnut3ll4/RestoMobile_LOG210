package com.gnut3ll4.restomobile;

import android.app.Activity;
import android.app.Application;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * Created by gnut3ll4 on 16/03/15.
 */
public class ApplicationManager extends Application {


    public static UserCredentials userCredentials;

    @Override
    public void onCreate() {
        super.onCreate();
        SecurePreferences securePreferences = new SecurePreferences(this);
        String u = securePreferences.getString(UserCredentials.USERNAME, "");
        String p = securePreferences.getString(UserCredentials.PASSWORD, "");

        if (u.length() > 0 && p.length() > 0) {
            userCredentials = new UserCredentials(u, p);
        }


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
