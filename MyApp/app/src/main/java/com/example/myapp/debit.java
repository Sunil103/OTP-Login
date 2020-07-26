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

public class debit extends AppCompatActivity {
    EditText cardno,cname,cvv,expiry;
    Button submit;
    String card,name,cvvno,expirydate;
    private static String URL_DEBIT = "https://103sunil.000webhostapp.com/Android/debit.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_debit);
        cardno = (EditText) findViewById(R.id.cardno);
        cname = (EditText) findViewById(R.id.name);
        expiry = (EditText) findViewById(R.id.expiry);
        cvv = (EditText) findViewById(R.id.cvv);

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
        name = this.cname.getText().toString().trim();
        cvvno = this.cvv.getText().toString().trim();
        expirydate = this.expiry.getText().toString().trim();
        submit = (Button) findViewById(R.id.submit);

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_DEBIT,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {

                            Log.i("tagconvertstr", "["+response+"]");
                            JSONObject jsonObject = new JSONObject(response);
                            String success = jsonObject.getString("success");
                            if(success.equals("1")){
                                Toast.makeText(debit.this,"Card Added Successfully!", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(debit.this,scan.class));


                            }
                        }
                        catch (JSONException e){
                            e.printStackTrace();
                            Toast.makeText(debit.this,"Register Failed!" + e.toString(), Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(debit.this,credit.class));

                            submit.setVisibility(View.VISIBLE);
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(debit.this,"Register Failed!" + error.toString(),
                                Toast.LENGTH_SHORT).show();

                        submit.setVisibility(View.VISIBLE);
                    }
                })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("cardnumber",card);
                params.put("name",name);
                params.put("cvv",cvvno);
                params.put("expiry",expirydate);


                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }

    public void btn_back(View view) {
        startActivity(new Intent(getApplicationContext(),payment.class));
    }
}


