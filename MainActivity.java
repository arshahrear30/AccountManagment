package com.arshahrear.accountmanagment;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {

    SharedPreferences sharedPreferences;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        sharedPreferences=getSharedPreferences("myApp",MODE_PRIVATE);


        try {
            MyMethods.MY_KEY=MyMethods.encryptData("arText","arPassword");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


        String email=sharedPreferences.getString("email","");

        //User একবার login করলে ঐ password SharedPreference এ if else দিয়ে store হবে। এখন ধরো ফাঁকা box ধরে রাখছি। ঐটায় email password box এর if else ফেলে বললাম ভিতরে কিছু থাকলে auto login হও, না থাকলে login page এ নিয়ে যাও।
        if (email.length()<=0){

            startActivity(new Intent(this,Login.class));
            finish();//finish() এটা এই activity ভুলিয়ে দেবে । তাহলে user login করলে এই activity-টায় back আর আসবে না ।
        }





    }
    //----------------------------
}
