package com.example.myapp;

import android.Manifest;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.zxing.Result;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

import static android.Manifest.permission_group.CAMERA;

public class scancode extends AppCompatActivity implements ZXingScannerView.ResultHandler {
    Button scan,scan2;
    private static String URL_REGIST = "https://103sunil.000webhostapp.com/Android/source_scan.php";
    public String mesg;
    private static final int REQUEST_CAMERA=1;
    private ZXingScannerView scannerView;
    Date date = new Date();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        scannerView = new ZXingScannerView(this);
        setContentView(scannerView);
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            if(checkPermission()){
                Toast.makeText(scancode.this,"Permission Granted!",Toast.LENGTH_LONG).show();
            }
            else{
                requestPermissions();
            }

        }

    }

    private boolean checkPermission(){
        return (ContextCompat.checkSelfPermission(scancode.this, Manifest.permission.CAMERA) == PackageManager.PERMISSION_GRANTED);
    }
    private void requestPermissions(){
        ActivityCompat.requestPermissions(this,new String[]{CAMERA},REQUEST_CAMERA);
    }
    public void onRequestPermissionResult(int requestCode,String permission[],int grantResults[]){
        switch(requestCode){
            case REQUEST_CAMERA:
                if(grantResults.length > 0){
                    boolean cameraAccepted = grantResults[0] == PackageManager.PERMISSION_GRANTED;
                    if(cameraAccepted){
                        Toast.makeText(scancode.this,"Permission Granted!",Toast.LENGTH_LONG).show();
                    }
                    else{
                        Toast.makeText(scancode.this,"Permission Denied!",Toast.LENGTH_LONG).show();
                        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
                            if(shouldShowRequestPermissionRationale(CAMERA)){
                                displayAlertMessage("You need to allow access for both permissions", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialogInterface, int i) {
                                        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                                            requestPermissions(new String[]{CAMERA}, REQUEST_CAMERA);
                                        }
                                    }
                                });
                                return;
                            }
                        }
                    }
                }
                break;
        }
    }
    public void displayAlertMessage(String message, DialogInterface.OnClickListener listener){

        new AlertDialog.Builder(scancode.this).setMessage(message).setPositiveButton("ok",listener).setNegativeButton("Cancel",null)
                .create().show();

    }
    @Override
    public void handleResult(final Result result) {
        final String scanResult = result.getText();
        System.out.println("Scan Result : "+scanResult);
        sourceScan(scanResult);
        AlertDialog.Builder builder= new AlertDialog.Builder(this);


        builder.setTitle("SCAN RESULT");
        builder.setPositiveButton("ok", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                //scannerView.resumeCameraPreview(scan.this);
                Intent a  = new Intent(scancode.this,scan.class);
                startActivity(a);
            }
        }).setNeutralButton("Visit", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(scanResult));
                startActivity(intent);
            }
        });
        builder.setMessage(scanResult);
        AlertDialog alert = builder.create();
        alert.show();



    }




    @Override
    protected void onResume() {
        super.onResume();

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M){
            if(checkPermission()){
                if(scannerView==null){
                    scannerView = new ZXingScannerView(this);
                    setContentView(scannerView);
                }
                scannerView.setResultHandler(this);
                scannerView.startCamera();
            }
            else{
                requestPermissions();
            }
        }
        scannerView.setResultHandler(this);
        scannerView.startCamera();
    }
    @Override
    public void onDestroy(){
        super.onDestroy();
        scannerView.stopCamera();
    }

    private void sourceScan(final String source){

        Toast.makeText(this,"Inside scan function : "+source,Toast.LENGTH_LONG).show();

        StringRequest stringRequest = new StringRequest(Request.Method.POST, URL_REGIST,
                new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
                        try {

                            Log.i("tagconvertstr", "[" + response + "]");
                            JSONObject jsonObject = new JSONObject(response);
                            String success = jsonObject.getString("success");
                            if (success.equals("1")) {
                                Toast.makeText(scancode.this, "Scan Success!", Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(scancode.this, scan.class));


                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(scancode.this, "Scan Failed!" + e.toString(), Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(scancode.this, Home.class));

                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(scancode.this, "Scan Failed!" + error.toString(),
                                Toast.LENGTH_SHORT).show();

                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("user_id", "123456789123");
                params.put("source", "Mysore Road");
                params.put("scan_time", date.toString());

                return params;
            }
        };
        RequestQueue requestQueue = Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);
    }
}
