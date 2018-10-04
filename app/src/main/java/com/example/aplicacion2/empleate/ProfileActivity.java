package com.example.aplicacion2.empleate;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.icu.util.Calendar;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        String id = getIntent().getExtras().getString("id");

        //ObtDatos(id);

       /* EditText nombre = (EditText) findViewById(R.id.editTextNombre);
        nombre.setText(id);*/

        final TextView DateNac = (TextView)findViewById(R.id.editTextFNac);

        DateNac.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onClick(View v) {
                Calendar c = Calendar.getInstance();
                int Dia = c.get(Calendar.DAY_OF_MONTH);
                int Mes = c.get(Calendar.MONTH);
                int Ano = c.get(Calendar.YEAR);

                DatePickerDialog dpt = new DatePickerDialog(ProfileActivity.this, new DatePickerDialog.OnDateSetListener() {
                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int day) {
                        String tDay = String.format("%0" + 2 + "d", day);
                        String tMes = String.format("%0" + 2 + "d", month);

                        DateNac.setText(tDay + "/" + tMes + "/" + year);
                    }
                }, Ano, Mes, Dia);
                dpt.show();
            }
        });

        // Get reference of widgets from XML layout
        final Spinner spinner = (Spinner) findViewById(R.id.spinnerEstudios);

        // Initializing a String Array
        String[] Estudios = {"Nivel de Estudios","ESO","Bachiller","CFGM","CFGS","Grado","Licenciatura"};

        final List<String> plantsList = new ArrayList<>(Arrays.asList(Estudios));

        // Initializing an ArrayAdapter
        final ArrayAdapter<String> spinnerArrayAdapter = new ArrayAdapter<String>(
                this,R.layout.support_simple_spinner_dropdown_item,Estudios){
            @Override
            public boolean isEnabled(int position){
                if(position == 0)
                {
                    // Disable the first item from Spinner
                    return false;
                }
                else
                {
                    return true;
                }
            }

            @Override
            public View getDropDownView(int position, View convertView,
                                        ViewGroup parent) {
                View view = super.getDropDownView(position, convertView, parent);
                TextView tv = (TextView) view;
                if(position == 0) {
                    // Set the disable item text color
                    tv.setTextColor(Color.GRAY);
                }
                else {
                    tv.setTextColor(Color.BLACK);
                }
                return view;
            }
        };

        spinnerArrayAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        spinner.setAdapter(spinnerArrayAdapter);

    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return false;
    }
/*
    public void ObtDatos(String id) {

        AdminSQLiteOpenHelper admin = new AdminSQLiteOpenHelper(this,null);

        SQLiteDatabase bd = admin.getWritableDatabase();


        Cursor fila = bd.rawQuery(
                "select * from profile where id=" + id, null);

        if (fila.moveToFirst()) {


        } else {

            Toast.makeText(this, "Usuario o Contraseña incorrecta",

                    Toast.LENGTH_SHORT).show();
        }
        bd.close();

    }*/

}
