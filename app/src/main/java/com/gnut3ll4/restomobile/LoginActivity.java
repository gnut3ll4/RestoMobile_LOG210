package com.gnut3ll4.restomobile;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBarDrawerToggle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.gnut3ll4.restomobile.model.User;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

/**
 * Created by gnut3ll4 on 16/03/15.
 */
public class LoginActivity extends ActionBarActivity implements Callback {

    private EditText loginEditText;
    private EditText passwordEditText;
    private Button loginButton;
    private UserCredentials userCredentials;
    private ActionBarDrawerToggle mDrawerToggle;

    private WebService service;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loginEditText = (EditText) findViewById(R.id.editText_login);
        passwordEditText = (EditText) findViewById(R.id.editText_password);

        loginButton = (Button) findViewById(R.id.button_login);


        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint(getString(R.string.server))
                .build();

       service = restAdapter.create(WebService.class);



        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userCredentials = new UserCredentials(loginEditText.getText().toString(), passwordEditText.getText().toString());
                service.login(loginEditText.getText().toString(),passwordEditText.getText().toString(),LoginActivity.this);
            }
        });



    }

    @Override
    public void success(Object o, Response response) {

//        User user = (User) o;
//
//        Toast.makeText(this,user.prenom+" "+user.nom,Toast.LENGTH_SHORT).show();


//        /*

        if (o != null) {
            User user = (User) o;
            if (user.getError() != null) {
                passwordEditText.setError(getString(R.string.error_invalid_pwd));
                passwordEditText.requestFocus();
            } else {
                Log.v("LoginActivity", "LoginActivity: o=" + o);
                ApplicationManager.userCredentials = userCredentials;

                SecurePreferences securePreferences = new SecurePreferences(this);
                securePreferences.edit().putString(userCredentials.USERNAME, userCredentials.getUsername()).commit();
                securePreferences.edit().putString(userCredentials.PASSWORD, userCredentials.getPassword()).commit();


                securePreferences.edit().putString("UserId", ""+user.getId()).commit();
                securePreferences.edit().putString("UserType", user.getType()+"").commit();
                securePreferences.edit().putString("UserPrenom",user.getPrenom()).commit();
                securePreferences.edit().putString("UserNom", user.getNom()).commit();
                securePreferences.edit().putString("UserAdresse", user.getAdresse()).commit();
                securePreferences.edit().putString("UserAltAdresse", user.getAltAdresse()).commit();
                securePreferences.edit().putString("UserTelephone", user.getTelephone()).commit();
                securePreferences.edit().putString("UserDateNaiss", user.getDateNaissance()).commit();










                
                
                finishActivity(1);
                startActivity(new Intent(this, MainActivity.class));
                finish();
            }

        } else {
            passwordEditText.setError(getString(R.string.error_invalid_email));
            passwordEditText.requestFocus();
        }


        //*/







    }

    @Override
    public void failure(RetrofitError error) {
        Toast.makeText(this,error.getMessage(),Toast.LENGTH_SHORT).show();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();
        if(id==R.id.action_settings)
        {
            Intent intent = new Intent(this, SettingsActivity.class);
            startActivity(intent);
            return true;
        }
        // Pass the event to ActionBarDrawerToggle, if it returns
        // true, then it has handled the app icon touch event
        else if (mDrawerToggle.onOptionsItemSelected(item)) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
