package com.gnut3ll4.restomobile;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TimePicker;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;


public class SendCommandeActivity extends ActionBarActivity {


    private EditText editTextDate;
    private EditText editTextTime;
    private DatePickerDialog datePickerDialog;
    private TimePickerDialog timePickerDialog;

    private SimpleDateFormat dateFormatter;
    private SimpleDateFormat timeFormatter;

    private RadioGroup radioGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_commande);

        radioGroup = (RadioGroup) findViewById(R.id.radiogroup);

        RadioButton radioButtonAdresse = new RadioButton(this);
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


        dateFormatter = new SimpleDateFormat("dd-MM-yyyy", Locale.CANADA_FRENCH);
        timeFormatter = new SimpleDateFormat("HH:mm",Locale.CANADA_FRENCH);

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
}
