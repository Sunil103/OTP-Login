package com.example.hammertag;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

public class login extends AppCompatActivity {
    ImageButton back,next;
    EditText phone;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    back=(ImageButton) findViewById(R.id.back);
    next=(ImageButton) findViewById(R.id.next);
    phone=findViewById(R.id.phone);



    back.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent i = new Intent(login.this,MainActivity.class);
            startActivity(i);
        }
    });

        findViewById(R.id.next).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String number = phone.getText().toString().trim();
                if (number.isEmpty() || number.length() < 10) {
                    phone.setError("Valid number is required");
                    phone.requestFocus();
                    return;
                }
                String phno = "+91"  + number;
                Intent intent = new Intent(login.this, otp.class);
                intent.putExtra("phno", phno);
                startActivity(intent);
            }
        });

    }



}
