package com.example.food4all;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
//import android.view.View;
//import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

//import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

//import com.google.android.gms.tasks.OnFailureListener;
//import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
//import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FieldValue;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class Contact extends AppCompatActivity {
    EditText name, email, message;
    Button submit;
    boolean isNameValid, isEmailValid, isMessageValid;
    FirebaseAuth fAuth;
    FirebaseFirestore fStore;
    String userID;
    public static final String TAG = "TAG";
    TextInputLayout nameError, emailError, messageError;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);
        name = findViewById(R.id.name);
        email = findViewById(R.id.email);
        message = findViewById(R.id.message);
        submit = findViewById(R.id.submit);
        nameError = findViewById(R.id.nameError);
        emailError = findViewById(R.id.emailError);
        messageError = findViewById(R.id.messageError);

        fAuth=FirebaseAuth.getInstance();
        fStore= FirebaseFirestore.getInstance();

        submit.setOnClickListener(v -> SetValidation());
    }
    public void SetValidation() {

        // Check for a valid name.
        if (name.getText().toString().isEmpty()) {
            nameError.setError(getResources().getString(R.string.name_error));
            isNameValid = false;
        } else  {
            isNameValid = true;
            nameError.setErrorEnabled(false);
        }

        // Check for a valid email address.
        if (email.getText().toString().isEmpty()) {
            emailError.setError(getResources().getString(R.string.email_error));
            isEmailValid = false;
        } else if (!Patterns.EMAIL_ADDRESS.matcher(email.getText().toString()).matches()) {
            emailError.setError(getResources().getString(R.string.error_invalid_email));
            isEmailValid = false;
        } else  {
            isEmailValid = true;
            emailError.setErrorEnabled(false);
        }

        // Check for a valid phone number.
        if (message.getText().toString().isEmpty()) {
            messageError.setError(getResources().getString(R.string.phone_error));
            isMessageValid = false;
        } else  {
            isMessageValid = true;
            messageError.setErrorEnabled(false);
        }

        if (isNameValid && isEmailValid && isMessageValid ) {

            String Name = name.getText().toString().trim();
            String Email= email.getText().toString().trim();
            String Message= message.getText().toString().trim();
            userID = Objects.requireNonNull(fAuth.getCurrentUser()).getUid();
            //DocumentReference DocumentReference = fStore.collection("donate").document(userID);
            CollectionReference collectionReference = fStore.collection("contact data");

            Map<String,Object> user = new HashMap<>();
            user.put("timestamp", FieldValue.serverTimestamp());
            user.put("name",Name);
            user.put("email",Email);
            user.put("message",Message);
            user.put("userid",userID);

            collectionReference.add(user)
                    .addOnSuccessListener(documentReference -> {
                        Toast.makeText(getApplicationContext(),"Success!",Toast.LENGTH_SHORT).show();
                        Log.d(TAG,"Successfully! We will shortly revert you back.");
                        startActivity(new Intent(getApplicationContext(),MainActivity.class));
                        Intent intent = new Intent(Contact.this, MainActivity.class);
                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                    })
                    .addOnFailureListener(e -> {
                        Toast.makeText(getApplicationContext(),"Error!",Toast.LENGTH_SHORT).show();
                        Log.w(TAG, "Error!", e);
                    });
        }

    }
}