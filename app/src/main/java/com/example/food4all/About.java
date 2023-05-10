package com.example.food4all;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;


import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
public class About extends AppCompatActivity {

    CardView instagram,facebook,twitter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);

        instagram = findViewById(R.id.instagram);
        facebook = findViewById(R.id.facebook);
        twitter = findViewById(R.id.twitter);

        instagram.setOnClickListener(v -> {
            Intent myWebLink = new Intent(Intent.ACTION_VIEW);
            myWebLink.setData(Uri.parse("http://www.instagram.com"));
            startActivity(myWebLink);
        });
        facebook.setOnClickListener(v -> {
            Intent myWebLink = new Intent(Intent.ACTION_VIEW);
            myWebLink.setData(Uri.parse("http://www.facebook.com"));
            startActivity(myWebLink);
        });
        twitter.setOnClickListener(v -> {
            Intent myWebLink = new Intent(Intent.ACTION_VIEW);
            myWebLink.setData(Uri.parse("http://www.twitter.com"));
            startActivity(myWebLink);
        });
    }

}