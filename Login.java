package com.arshahrear.accountmanagment;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.material.textfield.TextInputLayout;

import java.util.HashMap;
import java.util.Map;

public class Login extends AppCompatActivity {

    Button buttonSignup,buttonLogin;
    TextInputLayout inputEmail,inputPassword;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);

        buttonSignup=findViewById(R.id.buttonSignup);
        buttonLogin=findViewById(R.id.buttonLogin);
        inputEmail=findViewById(R.id.inputEmail);
        inputPassword=findViewById(R.id.inputPassword);


        buttonSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                startActivity(new Intent(Login.this,Signup.class));
                finish();

            }
        });


        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//====================
                String url = "https://nubsoft.xyz/apps/login.php";
                StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

//------------------------------------

                        if (response.contains("VALID LOGIN")) {

                            SharedPreferences sharedPreferences = getSharedPreferences("myApp", MODE_PRIVATE);
                            SharedPreferences.Editor editor = sharedPreferences.edit();
                            editor.putString("email", inputEmail.getEditText().getText().toString());
                            editor.apply();

                            startActivity(new Intent(Login.this, MainActivity.class));
                            finish();

                        } else {

                            new AlertDialog.Builder(Login.this)
                                    .setTitle("Server Response")
                                    .setMessage("response")
                                    .create()
                                    .show();


                        }
//------------------------------------


                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        new AlertDialog.Builder(Login.this)
                                .setTitle("Server Response")
                                .setMessage(error.getMessage())
                                .create()
                                .show();

                    }
                }) { //--শেষ 1st bracket এর পরে post method এ data পাঠাতে পারি। Right click > Generate > Overridemethod >getParams<Map>


                    @Nullable
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {

                        Map myMap = new HashMap<String, String>();
                        try {
                            myMap.put("email", MyMethods.encryptData(inputEmail.getEditText().getText().toString()));
                            myMap.put("password", MyMethods.encryptData(inputPassword.getEditText().getText().toString()));
                            myMap.put("key", MyMethods.MY_KEY);//encript kora lagbe na ..eta encript korai acay.


                        } catch (Exception e) {
                            throw new RuntimeException(e);
                        }




                        return myMap;
                    }
                };//--

                RequestQueue requestQueue = Volley.newRequestQueue(Login.this);
                requestQueue.add(stringRequest);
//====================

            }
        });



    }
    //--------------
}
