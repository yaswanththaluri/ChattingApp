package com.example.yaswanththaluri.chattingapp;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.yaswanththaluri.chattingapp.data.appContract;
import com.example.yaswanththaluri.chattingapp.data.appDbHelper;

public class Authentication extends AppCompatActivity {

    private String pin = "";
    private appDbHelper mDbHelper;
    int pinCount = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authentication);
        mDbHelper = new appDbHelper(this);
    }

    protected void onStart() {
        super.onStart();
        pinInfo();
//        try {
//            pinInfo();
//        }
//        catch (Exception e)
//        {
//            Toast.makeText(this, "Error in Authentication..!", Toast.LENGTH_SHORT).show();
//        }
    }

    public void pinInfo()
    {
        SQLiteDatabase db = mDbHelper.getReadableDatabase();

        String count = " select count(*) from settings";
        Cursor mCursor = db.rawQuery(count,null);
        mCursor.moveToFirst();
        int  icount = mCursor.getInt(0);
        if(icount>0)
        {
            String[] projection = {
                    appContract.appEntry.PIN,
            };

            Cursor cursor = db.query(
                   appContract.appEntry.TABLE_NAME,   // The table to query
                    projection,            // The columns to return
                    null,                  // The columns for the WHERE clause
                    null,                  // The values for the WHERE clause
                    null,                  // Don't group the rows
                    null,                  // Don't filter by row groups
                    null);



            try {
                int nameColumnIndex = cursor.getColumnIndex(appContract.appEntry.PIN);


                while (cursor.moveToNext()) {
                    // Use that index to extract the String or Int value of the word
                    // at the current row the cursor is on.
                    String currentPin = cursor.getString(nameColumnIndex);
                    pin = currentPin;
                }
            }
            finally {
                cursor.close();
            }
            authenticate();
        }
        else
        {
            Intent i = new Intent(Authentication.this, MainActivity.class);
            startActivity(i);
        }
        mCursor.close();
    }

    public void authenticate() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(Authentication.this);
        View mView = getLayoutInflater().inflate(R.layout.activity_lock, null);
        final EditText password = (EditText) mView.findViewById(R.id.lockpin);
        Button submit = (Button) mView.findViewById(R.id.confirm);
        final String oPin = pin;

        builder.setView(mView);
        final AlertDialog dialog = builder.create();
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String pin = password.getText().toString().trim();
                if (!pin.isEmpty()) {
                    if (oPin.equals(pin)) {
                        Toast.makeText(Authentication.this, "Login Successful", Toast.LENGTH_LONG).show();
                        dialog.dismiss();
                        Intent i = new Intent(Authentication.this, MainActivity.class);
                        startActivity(i);

                    } else {
                        if (pinCount != 3) {
                            pinCount += 1;
                            Toast.makeText(Authentication.this, "Login Failed...Try Again, Attempts left:"+(3-pinCount), Toast.LENGTH_SHORT).show();
                            password.setText("");
                        } else {
                            Toast.makeText(Authentication.this, "Login Attempts Exceeded", Toast.LENGTH_LONG).show();
                            finish();
                            System.exit(0);
                        }
                    }
                } else {
                    Toast.makeText(Authentication.this, "Pin Field Should not be empty", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}
