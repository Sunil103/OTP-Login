package com.example.myapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.navigation.NavigationView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class login extends AppCompatActivity  implements NavigationView.OnNavigationItemSelectedListener {
    EditText username, password;
    Button login;
    String user, pass;
    SharedPreferences preferences;
    private static String URL_LOGIN = "https://103sunil.000webhostapp.com/Android/login.php";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logindrawer);
        Toolbar toolbar = findViewById(R.id.toolbar);
        DrawerLayout drawer = findViewById(R.id.drawer);
        NavigationView navigationView = findViewById(R.id.nav);
        ActionBarDrawerToggle toggle1= new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.nav_open, R.string.nav_close);
        drawer.addDrawerListener(toggle1);
        toggle1.syncState();
        navigationView.setNavigationItemSelectedListener(this);
        username = findViewById(R.id.username);
        password = findViewById(R.id.password);
        login = findViewById(R.id.login);
        preferences = getSharedPreferences("myPrefs", MODE_PRIVATE);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(view == login) {
                    if (!validate()) {
                        LoginFun();
                    }
                }
                /*
                if (!user.isEmpty() || !pass.isEmpty()) {
                    LoginFun(user, pass);
                } else {
                    username.setError("Please enter username!");
                    password.setError("Please enter password!");

                }*/
            }
        });
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.scan, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        Fragment fragment = null;
        Intent i;

        int id = item.getItemId();

        if (id == R.id.home) {
            i=new Intent(this, Home.class);
            startActivity(i);
        } else if (id == R.id.pass) {
            i=new Intent(this,changepass.class);
            startActivity(i);
        } else if (id == R.id.payment) {
            i=new Intent(this,payment.class);
            startActivity(i);
        } else if (id == R.id.share) {

        } else if (id == R.id.logout) {

        }

        if(fragment!=null){
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction ft = fragmentManager.beginTransaction();
            ft.replace(R.id.screen_area,fragment);
            ft.commit();
        }
        DrawerLayout drawer = findViewById(R.id.drawer);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    private boolean validate(){
        if(username.getText().toString().trim().length() <= 0){
            Toast.makeText(login.this,"Please enter the username!",Toast.LENGTH_LONG).show();
            return true;
        }
        else if(password.getText().toString().trim().length() <= 0){
            Toast.makeText(login.this,"Please enter the password!",Toast.LENGTH_LONG).show();
            return true;
        }
        return false;
    }
    private void LoginFun() {
        user = this.username.getText().toString().trim();
        pass = this.password.getText().toString().trim();
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_LOGIN,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {
                            Log.d("TAG", "Length :- " + response.length());
                            Log.i("tagconvertstr", "[" + response + "]");
                            JSONObject jsonObject = new JSONObject(response);
                            Log.e("msg: ", response);
                            String success = jsonObject.getString("success");
                            JSONArray jsonArray = jsonObject.getJSONArray("login");
                            if (success.equals("1")) {
                                for (int i = 0; i < jsonArray.length(); i++) {
                                    JSONObject object = jsonArray.getJSONObject(i);
                                    String name = object.getString("name").trim();
                                    String email = object.getString("email").trim();
                                    String id = object.getString("id").trim();
                                    SharedPreferences details = getApplicationContext().getSharedPreferences("myPrefs", Context.MODE_PRIVATE);
                                    SharedPreferences.Editor editor = details.edit();
                                    editor.putString("username", name);
                                    editor.putString("usermail", email);
                                    editor.putString("userid", id);
                                    editor.apply();
                                    Toast.makeText(login.this, "Login Success!", Toast.LENGTH_SHORT).show();
                                    startActivity(new Intent(login.this, scan.class));
                                }
                            }else{
                                Toast.makeText(login.this, "Invalid username or password!!", Toast.LENGTH_SHORT).show();
                            }
                            } catch(JSONException e){
                                e.printStackTrace();
                                Toast.makeText(login.this, "Error!"+e.toString(), Toast.LENGTH_SHORT).show();
                            }

                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(login.this, "Error!"+error.toString(),Toast.LENGTH_SHORT).show();
                    }
                })
        {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String , String> params = new HashMap<>();
                params.put("username",user);
                params.put("password",pass);
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