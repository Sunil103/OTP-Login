package com.example.myapp;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
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

@SuppressWarnings("deprecation")
public class signup extends AppCompatActivity{
 EditText editTextName,editTextUname,editTextPass,editTextPass2,editTextEmail,editTextPhone,editTextAadhar;
 Button signup;
RadioButton male,female;
String name,user,password,pass2,email,phonenum,aadharnum;

    private static String URL_REGIST = "https://103sunil.000webhostapp.com/Android/register.php";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        editTextName = (EditText) findViewById(R.id.editTextName);
        editTextUname = (EditText) findViewById(R.id.editTextUname);
        editTextPass = (EditText) findViewById(R.id.editTextPass);
        editTextPass2 = (EditText)findViewById(R.id.editTextPass2);
        editTextEmail = (EditText) findViewById(R.id.editTextEmail);
        editTextPhone = (EditText) findViewById(R.id.editTextPhone);
        editTextAadhar = (EditText) findViewById(R.id.editTextAadhar);
        male = (RadioButton)findViewById(R.id.male);
        female = (RadioButton)findViewById(R.id.female);

        signup = (Button) findViewById(R.id.signup);

        signup.setOnClickListener(
                new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!validate()){
                    registerUser();
                }

            }

                    private boolean validate() {
                //String MobilePattern = "[0-9]{10}";
               // String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";
                        boolean invalid=false;
                        if(editTextName.getText().toString().trim().length() <= 0){
                            Toast.makeText(signup.this,"Please enter your NAME!",Toast.LENGTH_LONG).show();
                            return true;
                        }
                        else if(editTextUname.getText().toString().trim().length() <= 0){
                            Toast.makeText(signup.this,"Please enter your USERNAME!",Toast.LENGTH_LONG).show();
                            return true;
                        }
                        else if(editTextPass.getText().toString().trim().length() <= 0){
                            Toast.makeText(signup.this,"Please enter your PASSWORD!",Toast.LENGTH_LONG).show();
                            return true;
                        }
                        else if(editTextPass2.getText().toString().trim().length() <= 0){
                            Toast.makeText(signup.this,"Please confirm your  PASSWORD!",Toast.LENGTH_LONG).show();
                            return true;
                        }
                        else if(editTextEmail.getText().toString().trim().length() <= 0){
                            Toast.makeText(signup.this,"Please enter your EMAIL!",Toast.LENGTH_LONG).show();
                            return true;
                        }
                        else if(editTextPhone.getText().toString().trim().length() <= 0){
                            Toast.makeText(signup.this,"Please enter your PHONE NUMBER!",Toast.LENGTH_LONG).show();
                            return true;
                        }
                        else if(editTextAadhar.getText().toString().trim().length() <= 0){
                            Toast.makeText(signup.this,"Please enter your AADHAR NUMBER!",Toast.LENGTH_LONG).show();
                            return true;
                        }
                        else if(editTextAadhar.getText().toString().trim().length() <= 0){
                            Toast.makeText(signup.this,"Please enter your AADHAR NUMBER!",Toast.LENGTH_LONG).show();
                            return true;
                        }
                        else if(editTextPass.length()<6){
                            invalid = true;
                            Toast.makeText(signup.this,"PASSWORD must be at least 6 characters!",Toast.LENGTH_LONG).show();
                            return true;
                        }
                        else if(editTextAadhar.length()<12 || editTextAadhar.length()>12){
                            invalid = true;
                            Toast.makeText(signup.this,"AADHAR number should be exactly 12 digits!",Toast.LENGTH_LONG).show();
                            return true;
                        }
                        else if(!editTextPass.getText().toString().equals(editTextPass2.getText().toString())){
                            invalid = true;
                            Toast.makeText(signup.this,"PASSWORD DONT MATCH",Toast.LENGTH_LONG).show();
                            return true;
                        }
                        return false;
                    }
                });

    }
    private void registerUser(){

         name = this.editTextName.getText().toString().trim();
         user = this.editTextUname.getText().toString().trim();
         password = this.editTextPass.getText().toString().trim();
        email = this.editTextEmail.getText().toString().trim();
        phonenum = this.editTextPhone.getText().toString().trim();
         aadharnum = this.editTextAadhar.getText().toString().trim();
        pass2 = this.editTextPass2.getText().toString().trim();


            StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_REGIST,
                    new Response.Listener<String>() {
                        @Override
                        public void onResponse(String response) {
                            try {

                                Log.i("tagconvertstr", "[" + response + "]");
                                JSONObject jsonObject = new JSONObject(response);
                                String success = jsonObject.getString("success");
                                if (success.equals("1")) {
                                    Toast.makeText(signup.this, "Register Success!", Toast.LENGTH_SHORT).show();
                                    startActivity(new Intent(signup.this, login.class));


                                }
                            } catch (JSONException e) {
                                e.printStackTrace();
                                Toast.makeText(signup.this, "Register Failed!" + e.toString(), Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(signup.this, Home.class));

                                signup.setVisibility(View.VISIBLE);
                            }
                        }
                    },
                    new Response.ErrorListener() {
                        @Override
                        public void onErrorResponse(VolleyError error) {
                            Toast.makeText(signup.this, "Register Failed!" + error.toString(),
                                    Toast.LENGTH_SHORT).show();

                            signup.setVisibility(View.VISIBLE);
                        }
                    }) {
                @Override
                protected Map<String, String> getParams() throws AuthFailureError {
                    Map<String, String> params = new HashMap<>();
                    params.put("name", name);
                    params.put("username", user);
                    params.put("password", password);
                    params.put("email", email);
                    params.put("phone", phonenum);
                    params.put("aadhar", aadharnum);

                    return params;
                }
            };
            RequestQueue requestQueue = Volley.newRequestQueue(this);
            requestQueue.add(stringRequest);
        }



    public void btn_back(View view) {
        startActivity(new Intent(getApplicationContext(), Home.class));

    }
}
