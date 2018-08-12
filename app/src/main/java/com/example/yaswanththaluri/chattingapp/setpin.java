package com.example.yaswanththaluri.chattingapp;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.yaswanththaluri.chattingapp.data.appContract;
import com.example.yaswanththaluri.chattingapp.data.appDbHelper;

public class setpin extends AppCompatActivity {

    public String pin;
    public String cnfpin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setpin);

        final EditText t = (EditText) findViewById(R.id.entlockpin);
        final EditText t2 = (EditText) findViewById(R.id.cnflockpin);


        Button b = (Button) findViewById(R.id.confirm);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pin = t.getText().toString();
                cnfpin = t2.getText().toString();
                if (pin.equals(cnfpin) && !pin.equals(""))
                {
                    pinset();
                    Intent i = new Intent(setpin.this, MainActivity.class);
                    startActivity(i);
                }
            }
        });
    }

    public void pinset()
    {
        appDbHelper mDbHelper = new appDbHelper(this);
        SQLiteDatabase db = mDbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();


        values.put(appContract.appEntry.PIN,pin);


        long newRowId;

//        newRowId = db.insert(appEntry.TABLE_NAME, null, values);
        newRowId = db.update(appContract.appEntry.TABLE_NAME,values,"background=background",null);

        if (newRowId == 0)
        {
            values.put(appContract.appEntry.RES_ID,R.drawable.wallpaper_default);
            values.put(appContract.appEntry.BACKGROUND,"background");
            newRowId = db.insert(appContract.appEntry.TABLE_NAME, null, values);
        }

        if (newRowId == -1) {
            // If the row ID is -1, then there was an error with insertion.
            Toast.makeText(this, "Error with saving Customer", Toast.LENGTH_SHORT).show();
        } else {
            // Otherwise, the insertion was successful and we can display a toast with the row ID.
//            Toast.makeText(this, "Details saved successfully" + newRowId, Toast.LENGTH_LONG).show();
        }
    }
}
