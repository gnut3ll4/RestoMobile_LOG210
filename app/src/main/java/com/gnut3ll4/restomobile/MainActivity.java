package com.gnut3ll4.restomobile;

import android.app.Application;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;


public class MainActivity extends ActionBarActivity implements Callback {

    private TextView tvTest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);




        tvTest = (TextView) findViewById(R.id.tv_test);


        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint(getString(R.string.server))
                .build();

        WebService service = restAdapter.create(WebService.class);


        service.helloWorld(this);




    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 0) {
            if (resultCode > 0) {
                Bundle extras = data.getExtras();
                String codeU = extras.getString(UserCredentials.USERNAME);
                String codeP = extras.getString(UserCredentials.PASSWORD);
                ApplicationManager.userCredentials = new UserCredentials(new SecurePreferences(this));
            }
        }
    }


    @Override
    protected void onResume() {
        super.onResume();
        if (ApplicationManager.userCredentials == null) {

			Intent intent = new Intent(this, LoginActivity.class);
			startActivityForResult(intent, 0);

        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.


        switch (item.getItemId()) {
            case R.id.action_disconnect:
                ApplicationManager.deconnexion(this);
                return true;


        }

        int id = item.getItemId();





        return super.onOptionsItemSelected(item);
    }

    @Override
    public void success(Object o, Response response) {

        TestHello testHello = (TestHello) o;

        tvTest.setText(testHello.hello);
    }

    @Override
    public void failure(RetrofitError error) {
        tvTest.setText(error.getMessage());
    }
}
