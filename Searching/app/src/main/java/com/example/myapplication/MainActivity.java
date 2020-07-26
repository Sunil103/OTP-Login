package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements AdapterView.OnItemSelectedListener {
    String[] city = { "select one","Bangalore", "Mumbai", "Chennai", "Delhi", "Kolkata" };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Spinner spin = (Spinner) findViewById(R.id.spinner1);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, city);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spin.setAdapter(adapter);
        spin.setOnItemSelectedListener(this);
    }
    @Override
    public void onItemSelected(AdapterView<?> arg0, View arg1, int position,long id) {


        if(position==1){
            Intent i = new Intent(this,Bangalore.class);
            Toast.makeText(getApplicationContext(), "Selected city: "+city[position] ,Toast.LENGTH_SHORT).show();
            startActivity(i);
        }else if(position==2){
            Intent i = new Intent(this,Mumbai.class);
            Toast.makeText(getApplicationContext(), "Selected city: "+city[position] ,Toast.LENGTH_SHORT).show();
            startActivity(i);
        }else if(position==3){
            Intent i = new Intent(this,Chennai.class);
            Toast.makeText(getApplicationContext(), "Selected city: "+city[position] ,Toast.LENGTH_SHORT).show();
            startActivity(i);
        }else if(position==4){
            Intent i = new Intent(this,Delhi.class);
            Toast.makeText(getApplicationContext(), "Selected city: "+city[position] ,Toast.LENGTH_SHORT).show();
            startActivity(i);
        }else if(position==5){
            Intent i = new Intent(this,Kolkata.class);
            Toast.makeText(getApplicationContext(), "Selected city: "+city[position] ,Toast.LENGTH_SHORT).show();
            startActivity(i);
        }

    }
    @Override
    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO - Custom Code
    }
}