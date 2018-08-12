package com.example.yaswanththaluri.chattingapp;

import java.text.SimpleDateFormat;
import java.util.Calendar;

public class FriendlyMessage {
    private String text;
    private String name;
    //    private String time;
    private String photoUrl;

    public FriendlyMessage() {
    }

    public FriendlyMessage(String text, String name, String photoUrl) {
        this.text = text;
        this.name = name;
        this.photoUrl = photoUrl;
    }

    public String getText() {
        return text;
    }

//    public String getTime()
//    {
//        return time;
//    }

    public void setText(String text) {
        this.text = text;
    }

//    public void setTime()
//    {
//        Calendar calendar = Calendar.getInstance();
//        SimpleDateFormat mdformat = new SimpleDateFormat("HH:mm:ss");
//        time =  mdformat.format(calendar.getTime());
//    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }
}
