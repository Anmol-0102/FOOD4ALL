package com.example.food4all;

import android.content.Intent;
import android.os.Bundle;
//import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.google.firebase.auth.FirebaseAuth;

public class landingpage extends AppCompatActivity {

    CardView login,register,about;
    FirebaseAuth fAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_landingpage);

        login = findViewById(R.id.cardLogin);
        register = findViewById(R.id.cardRegister);
        about = findViewById(R.id.cardAboutus);

        fAuth= FirebaseAuth.getInstance();
        if(fAuth.getCurrentUser() !=null){
            Intent intent = new Intent(landingpage.this, MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }

        login.setOnClickListener(v -> startActivity(new Intent(getApplicationContext(), Logup.class)));
        register.setOnClickListener(v -> startActivity(new Intent(getApplicationContext(), Signup.class)));
        about.setOnClickListener(v -> startActivity(new Intent(getApplicationContext(), About.class)));
    }

}