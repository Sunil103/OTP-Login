package com.example.myapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class credit extends AppCompatActivity {
    EditText cardno,type,expiry;
    Button submit;
    String card,cardtype,expirydate;
    private static String URL_CREDIT = "https://103sunil.000webhostapp.com/Android/credit.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_credit);
        cardno = (EditText) findViewById(R.id.cardno);
        type = (EditText) findViewById(R.id.type);
        expiry = (EditText) findViewById(R.id.expiry);
        submit = (Button) findViewById(R.id.submit);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                addcard();
            }
        });
    }
    private void addcard(){
        card= this.cardno.getText().toString().trim();
        cardtype = this.type.getText().toString().trim();
        expirydate = this.expiry.getText().toString().trim();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_CREDIT,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {

                            Log.i("tagconvertstr", "["+response+"]");
                            JSONObject jsonObject = new JSONObject(response);
                            String success = jsonObject.getString("success");
                            if(success.equals("1")){
                                Toast.makeText(credit.this,"Card Added Successfully!", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(credit.this,scan.class));


                            }
                        }
                        catch (JSONException e){
                            e.printStackTrace();
                            Toast.makeText(credit.this,"Register Failed!" + e.toString(), Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(credit.this,credit.class));

                            submit.setVisibility(View.VISIBLE);
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(credit.this,"Register Failed!" + error.toString(),
                                Toast.LENGTH_SHORT).show();

                        submit.setVisibility(View.VISIBLE);
                    }
                })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("cardnumber",card);
                params.put("type",cardtype);
                params.put("expiry",expirydate);


                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
    public void btn_creditback(View view) {
        startActivity(new Intent(getApplicationContext(),payment.class));
    }
    }


