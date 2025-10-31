package com.arshahrear.accountmanagment;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

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
import com.google.android.material.textfield.TextInputEditText;

import java.io.ByteArrayOutputStream;
import android.util.Base64;
import java.util.HashMap;
import java.util.Map;


public class Signup extends AppCompatActivity {

    ImageView imageProfile;
    TextView tvChangePhoto;
    Button buttonSignup,buttonLogin;
    TextInputEditText inputEmail,inputPassword,inputName;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_signup);

        imageProfile=findViewById(R.id.imageProfile);
        tvChangePhoto=findViewById(R.id.tvChangePhoto);
        buttonSignup=findViewById(R.id.buttonSignup);
        buttonLogin=findViewById(R.id.buttonLogin);
        inputEmail=findViewById(R.id.inputEmail);
        inputPassword=findViewById(R.id.inputPassword);
        inputName=findViewById(R.id.inputName);

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Signup.this,Login.class));
                finish();

            }
        });

        buttonSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String email=inputEmail.getText().toString();
                String password=inputPassword.getText().toString();
                String name=inputName.getText().toString();

                BitmapDrawable bitmapDrawable=(BitmapDrawable) imageProfile.getDrawable();
                Bitmap bitmap=bitmapDrawable.getBitmap();
                ByteArrayOutputStream outputStream=new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.JPEG,100,outputStream);

                byte[] imageBytes=outputStream.toByteArray();
                String image= Base64.encodeToString(imageBytes,Base64.DEFAULT);//import java.util.Base64;এর জায়গায় import android.util.Base64; এইটা replace করলে error যাবে

                String url="https://nubsoft.xyz/apps/signup.php";
                StringRequest stringRequest=new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

//------------------------------------

                        if (response.contains("Signup Success")) {

                            SharedPreferences sharedPreferences = getSharedPreferences("myApp", MODE_PRIVATE);
                            SharedPreferences.Editor editor = sharedPreferences.edit();
                            editor.putString("email", email);
                            editor.apply();

                            startActivity(new Intent( Signup.this, MainActivity.class));
                            finish();

                        }
//------------------------------------


                        new AlertDialog.Builder(Signup.this)
                                .setTitle("Server Response")
                                .setMessage("response")
                                .create()
                                .show();


                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        new AlertDialog.Builder(Signup.this)
                                .setTitle("Server Response")
                                .setMessage(error.getMessage())
                                .create()
                                .show();

                    }
                }){ //--শেষ 1st bracket এর পরে post method এ data পাঠাতে পারি। Right click > Generate > Overridemethod >getParams<Map>


                    @Nullable
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {

                        Map myMap = new HashMap<String,String>();
                        try {
                            myMap.put("email",MyMethods.encryptData(inputEmail.getText().toString()));
                            myMap.put("password",MyMethods.encryptData(inputPassword.getText().toString()));

                        } catch (Exception e) {
                            throw new RuntimeException(e);
                        }

                        myMap.put("name",name);
                        myMap.put("image",image);
                        myMap.put("key",MyMethods.MY_KEY);


                        return myMap;
                    }
                };//--

                RequestQueue requestQueue= Volley.newRequestQueue(Signup.this);
                requestQueue.add(stringRequest);

            }
        });



    }
    //-----------
}
