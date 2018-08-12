package com.example.yaswanththaluri.chattingapp;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;
import com.example.yaswanththaluri.chattingapp.data.appContract.appEntry;
import com.example.yaswanththaluri.chattingapp.data.appDbHelper;

public class ChangeWallpaper extends AppCompatActivity {

    public int resid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_wallpaper);

        ImageView wallpaper1 = (ImageView) findViewById(R.id.wallpaper1);
        wallpaper1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                confirm(R.drawable.wallpaper_one);
            }
        });

        ImageView wallpaper2 = (ImageView) findViewById(R.id.wallpaper2);
        wallpaper2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                confirm(R.drawable.wallpaper_two);
            }
        });

        ImageView wallpaper3 = (ImageView) findViewById(R.id.wallpaper3);
        wallpaper3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                confirm(R.drawable.wallpaper_three);
            }
        });

        ImageView wallpaper4 = (ImageView) findViewById(R.id.wallpaper4);
        wallpaper4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                confirm(R.drawable.wallpaper_four);
            }
        });

    }

    public void confirm(final int id) {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(ChangeWallpaper.this);
        alertDialog.setTitle("Confirm Changes?");
        alertDialog.setMessage("Are you sure you want Change the Wallpaper?");

        alertDialog.setPositiveButton("YES", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                RelativeLayout r = (RelativeLayout) findViewById(R.id.mainLayout);
                Toast.makeText(getApplicationContext(), "Wallpaper Changed Successfully...", Toast.LENGTH_LONG).show();
                Intent i = new Intent(ChangeWallpaper.this, MainActivity.class);
                resid = id;
                save();
                startActivity(i);
            }
        });

        alertDialog.setNegativeButton("NO", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                // Write your code here to invoke NO event

                dialog.cancel();
            }
        });
        alertDialog.show();
    }

    private void save()
    {
        appDbHelper mDbHelper = new appDbHelper(this);
        SQLiteDatabase db = mDbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(appEntry.BACKGROUND,"background");
        values.put(appEntry.RES_ID,resid);


        long newRowId;

//        newRowId = db.insert(appEntry.TABLE_NAME, null, values);
        newRowId = db.update(appEntry.TABLE_NAME,values,"background=background",null);

        if (newRowId == 0)
        {
            values.put(appEntry.PIN,"0000");
            newRowId = db.insert(appEntry.TABLE_NAME, null, values);
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
