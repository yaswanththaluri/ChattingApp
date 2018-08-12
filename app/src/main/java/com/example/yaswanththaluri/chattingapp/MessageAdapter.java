package com.example.yaswanththaluri.chattingapp;

import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;

import java.util.List;

import static com.example.yaswanththaluri.chattingapp.R.drawable.msgcornersreceiver;

public class MessageAdapter extends ArrayAdapter<FriendlyMessage> {
    private FirebaseAuth mFirebseAuth;
    public MessageAdapter(Context context, int resource, List<FriendlyMessage> objects) {
        super(context, resource, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = ((Activity) getContext()).getLayoutInflater().inflate(R.layout.item_message, parent, false);
        }

        ImageView photoImageView = (ImageView) convertView.findViewById(R.id.photoImageView);
        TextView messageTextView = (TextView) convertView.findViewById(R.id.messageTextView);
        TextView authorTextView = (TextView) convertView.findViewById(R.id.nameTextView);

        FriendlyMessage message = getItem(position);

        boolean isPhoto = message.getPhotoUrl() != null;
        if (isPhoto) {
            messageTextView.setVisibility(View.GONE);
            photoImageView.setVisibility(View.VISIBLE);
            Glide.with(photoImageView.getContext())
                    .load(message.getPhotoUrl())
                    .into(photoImageView);
        } else {
            messageTextView.setVisibility(View.VISIBLE);
            photoImageView.setVisibility(View.GONE);
            messageTextView.setText(message.getText());
        }
        authorTextView.setText(message.getName());
        mFirebseAuth = FirebaseAuth.getInstance();
//        Log.i("User",mFirebseAuth.getCurrentUser().getDisplayName()+"\n "+message.getName());
        if(mFirebseAuth.getCurrentUser().getDisplayName().equals(message.getName()))
        {
            LinearLayout l =(LinearLayout) convertView.findViewById(R.id.listMain);
            LinearLayout l2 =(LinearLayout) convertView.findViewById(R.id.msg_layout);
            l.setGravity(Gravity.RIGHT);
            l.setPadding(150,10,20,10);
            l2.setBackgroundResource(R.drawable.msgcornersreceiver);
        }
        else
        {
            LinearLayout l =(LinearLayout) convertView.findViewById(R.id.listMain);
            LinearLayout l2 =(LinearLayout) convertView.findViewById(R.id.msg_layout);
            l2.setBackgroundResource(R.drawable.msgcorners);
            l.setGravity(Gravity.LEFT);
            l.setPadding(20,10,150,10);
        }
        return convertView;
    }
}

