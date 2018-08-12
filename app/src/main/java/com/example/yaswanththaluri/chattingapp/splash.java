package com.example.yaswanththaluri.chattingapp;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class splash extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        ActionBar b = getSupportActionBar();
        if (b != null) {
            b.hide();
        }

        Thread myThread = new Thread()
        {
            public void run()
            {
                try {
                    sleep(3000);
                    Intent i = new Intent(getApplicationContext(),Authentication.class);
                    startActivity(i);
                    finish();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        myThread.start();
    }
}
