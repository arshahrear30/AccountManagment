package com.arshahrear.accountmanagment;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.bumptech.glide.Glide;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    SharedPreferences sharedPreferences;
    TextView tvDisplay;
    ImageView imageView;
    Button buttonLogout;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        sharedPreferences=getSharedPreferences("myApp",MODE_PRIVATE);

        tvDisplay=findViewById(R.id.tvDisplay);
        imageView=findViewById(R.id.imageView);
        buttonLogout=findViewById(R.id.buttonLogout);


        try {
            MyMethods.MY_KEY = MyMethods.encryptData("arText1122");
            Log.d("ENCRYPTED_KEY", MyMethods.MY_KEY);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


        String email=sharedPreferences.getString("email","");

        //User একবার login করলে ঐ password SharedPreference এ if else দিয়ে store হবে। এখন ধরো ফাঁকা box ধরে রাখছি। ঐটায় email password box এর if else ফেলে বললাম ভিতরে কিছু থাকলে auto login হও, না থাকলে login page এ নিয়ে যাও।
        if (email.length()<=0){

            startActivity(new Intent(MainActivity.this,Login.class));
            finish();//finish() এটা এই activity ভুলিয়ে দেবে । তাহলে user login করলে এই activity-টায় back আর আসবে না ।
        }else{

            objectRequest();

        }

        //button er kaz
        buttonLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor=sharedPreferences.edit();
                editor.putString("email","");
                editor.apply();

                startActivity(new Intent(MainActivity.this,Login.class));
                finish();
            }
        });




    }
    //----------------------------


    //ppppppppppppppppppppp

    private void objectRequest() {
        String url = "https://nubsoft.xyz/apps/home.php";

        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("key", MyMethods.MY_KEY);
            jsonObject.put( "email", sharedPreferences.getString( "email", ""));

        } catch (JSONException e) {
            throw new RuntimeException(e);
        }

        JsonObjectRequest objectRequest = new JsonObjectRequest(Request.Method.POST, url, jsonObject, new Response.Listener<JSONObject>() {
        @Override
        public void onResponse(JSONObject response){

            try {

                String result = response.getString("result");
                String image = response.getString("image");

                tvDisplay.setText(result);

                Glide.with(MainActivity.this)
                        .load(image)
                        .into(imageView);







            } catch (JSONException e) {
                throw new RuntimeException(e);
            }

        }

        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

                new AlertDialog.Builder(MainActivity.this)
                        .setTitle("Server not Response")
                        .setMessage(error != null ? error.toString() : "Unknown error")
                        .create()
                        .show();

            }
        });

        RequestQueue requestQueue = Volley.newRequestQueue( MainActivity.this);
        requestQueue.add(objectRequest);
    }

    //ppppppppppppppppppppp

}
