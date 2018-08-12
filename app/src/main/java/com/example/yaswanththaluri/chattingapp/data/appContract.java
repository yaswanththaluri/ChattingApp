package com.example.yaswanththaluri.chattingapp.data;

import android.provider.BaseColumns;

public class appContract
{
    private appContract()
    {}
    public static final class appEntry implements BaseColumns
    {
        public static final String TABLE_NAME = "settings";

        public static final String BACKGROUND = "background";

        public static final String RES_ID = "resid";

        public static final String PIN = "pin";
    }
}
