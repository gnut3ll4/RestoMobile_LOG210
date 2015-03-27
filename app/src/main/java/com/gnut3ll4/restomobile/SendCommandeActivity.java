package com.gnut3ll4.restomobile;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TimePicker;
import android.widget.Toast;

import com.gnut3ll4.restomobile.model.Plat;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;


public class SendCommandeActivity extends ActionBarActivity implements Callback {


    private EditText editTextDate;
    private EditText editTextTime;
    private DatePickerDialog datePickerDialog;
    private TimePickerDialog timePickerDialog;

    private SimpleDateFormat dateFormatter;
    private SimpleDateFormat timeFormatter;

    private RadioGroup radioGroup;

    private Button btnSendCommande;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_commande);

        radioGroup = (RadioGroup) findViewById(R.id.radiogroup);

        final RadioButton radioButtonAdresse = new RadioButton(this);
        radioButtonAdresse.setText(ApplicationManager.user.getAdresse());
        radioGroup.addView(radioButtonAdresse);

        String altAdresse = ApplicationManager.user.getAltAdresse();

        if(altAdresse!=null && altAdresse.length()>0 ) {
            RadioButton radioButtonAltAdresse = new RadioButton(this);
            radioButtonAltAdresse.setText(altAdresse);
            radioGroup.addView(radioButtonAltAdresse);
        }

        editTextDate = (EditText) findViewById(R.id.etxt_date);
        editTextDate.setInputType(InputType.TYPE_NULL);
        editTextDate.requestFocus();

        editTextTime = (EditText) findViewById(R.id.etxt_heure);
        editTextTime.setInputType(InputType.TYPE_NULL);
        editTextTime.requestFocus();


        dateFormatter = new SimpleDateFormat("yyyy-MM-dd", Locale.CANADA_FRENCH);
        timeFormatter = new SimpleDateFormat("HH:mm:ss",Locale.CANADA_FRENCH);

        editTextDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(view == editTextDate) {
                    datePickerDialog.show();
                }
            }
        });

        final Calendar newCalendar = Calendar.getInstance();
        datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {

            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                editTextDate.setText(dateFormatter.format(newDate.getTime()));
            }

        },newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));


        editTextTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timePickerDialog.show();
            }
        });


        timePickerDialog = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH),hourOfDay,minute);
                editTextTime.setText(timeFormatter.format(newDate.getTime()));
            }
        },newCalendar.get(Calendar.HOUR_OF_DAY),newCalendar.get(Calendar.MINUTE),true);

        btnSendCommande = (Button) findViewById(R.id.btn_commande);

        btnSendCommande.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                RestAdapter restAdapter = new RestAdapter.Builder()
                        .setEndpoint(getString(R.string.server))
                        .build();

                WebService service = restAdapter.create(WebService.class);

                String username = ApplicationManager.userCredentials.getUsername();
                String password = ApplicationManager.userCredentials.getPassword();




                String date = editTextDate.getText().toString() +" "+ editTextTime.getText().toString();


                RadioButton radioButtonRequest = (RadioButton) findViewById(radioGroup.getCheckedRadioButtonId());

                String adresse = radioButtonRequest.getText().toString();

                JSONArray jsonArray = new JSONArray();

                JSONObject jsonObject = new JSONObject();


                for(Map.Entry<Plat,Integer> entry : ApplicationManager.panier.entrySet()){
                    try {
                        jsonObject = new JSONObject();
                        jsonObject.put("id",""+entry.getKey().getId());
                        jsonObject.put("qte",""+entry.getValue());
                        jsonArray.put(jsonObject);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }

                service.ajouterCommande(username,password,date,adresse,jsonArray,SendCommandeActivity.this);

            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_send_commande, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void success(Object o, Response response) {
        Toast.makeText(this,"Votre commande a bien été prise en compte !",Toast.LENGTH_SHORT).show();
        ApplicationManager.panier = new HashMap<>();
        Intent i = new Intent(SendCommandeActivity.this, MainActivity.class);
        SendCommandeActivity.this.startActivity(i);
    }

    @Override
    public void failure(RetrofitError error) {
        Toast.makeText(this,error.getMessage(),Toast.LENGTH_SHORT).show();
    }
}

