package com.example.yaswanththaluri.chattingapp.data;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class appDbHelper extends SQLiteOpenHelper {

    public static final String LOG_TAG = appDbHelper.class.getSimpleName();

    /** Name of the database file */
    private static final String DATABASE_NAME = "app.db";

    /**
     * Database version. If you change the database schema, you must increment the database version.
     */
    private static final int DATABASE_VERSION = 1;

    /**
     * Constructs a new instance of {@link appDbHelper}.
     *
     * @param context of the app
     */
    public appDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    /**
     * This is called when the database is created for the first time.
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        // Create a String that contains the SQL statement to create the pets table
        String SQL_CREATE_APP_TABLE =  "CREATE TABLE " + appContract.appEntry.TABLE_NAME + " ("
                + appContract.appEntry.BACKGROUND + " TEXT NOT NULL, "
                + appContract.appEntry.RES_ID + " INTEGER NOT NULL,"
                + appContract.appEntry.PIN + " TEXT NOT NULL);";

        // Execute the SQL statement
        db.execSQL(SQL_CREATE_APP_TABLE);
    }

    /**
     * This is called when the database needs to be upgraded.
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // The database is still at version 1, so there's nothing to do be done here.
    }
}
