package com.example.myapp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

public class payment extends AppCompatActivity implements  View.OnClickListener{
    private CardView creditcard,debitcard,exitcard;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);
        creditcard = (CardView) findViewById(R.id.credit);
        debitcard = (CardView) findViewById(R.id.debit);
        exitcard = (CardView) findViewById(R.id.exit);

        creditcard.setOnClickListener(this);
        debitcard.setOnClickListener(this);
        exitcard.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        Intent i;
        switch(view.getId()){
            case R.id.credit : i=new Intent(this,credit.class);
                startActivity(i);
                break;
            case R.id.debit : i=new Intent(this,debit.class);
                startActivity(i);
                break;
            case R.id.exit : i=new Intent(this,login.class);
                startActivity(i);
                break;
        }
    }
}
