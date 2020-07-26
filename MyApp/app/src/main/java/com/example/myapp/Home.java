package com.example.myapp;
import android.content.Intent;
import android.os.Bundle;

import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

public class Home extends AppCompatActivity implements View.OnClickListener{
    private CardView logincard,signupcard,detailscard,aboutcard,exitcard;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //defining cards
        logincard = (CardView) findViewById(R.id.login);
        signupcard = (CardView) findViewById(R.id.signup);
        detailscard = (CardView) findViewById(R.id.details);
        aboutcard = (CardView) findViewById(R.id.about);
        exitcard = (CardView) findViewById(R.id.exit);

        //adding click listeners
        logincard.setOnClickListener(this);
        signupcard.setOnClickListener(this);
        detailscard.setOnClickListener(this);
        aboutcard.setOnClickListener(this);
        exitcard.setOnClickListener(this);


    }
    @Override
    public void onClick(View v){

        Intent i;

        switch(v.getId()){
            case R.id.login : i=new Intent(this,login.class);
                startActivity(i);
                break;
            case R.id.signup : i=new Intent(this,signup.class);
                startActivity(i);
                break;
            case R.id.details : i=new Intent(this,details.class);
                startActivity(i);
                break;
            case R.id.about: i=new Intent(this,about.class);
                startActivity(i);
                break;
        }
    }
}
