package com.example.savorysecrets;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class ProfilePage extends AppCompatActivity {

    private Button updateProfileButton;
    private TextInputEditText editFirstName;
    private TextInputEditText editLastName;
    private TextInputEditText editEmail;
    private TextInputEditText editPhoneNumber;

    private DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_page);

        updateProfileButton=findViewById(R.id.updateProfileButton);
        editFirstName=findViewById(R.id.editFirstName);
        editLastName=findViewById(R.id.editLastName);
        editEmail=findViewById(R.id.editEmail);
        editPhoneNumber=findViewById(R.id.editPhoneNumber);

        reference = FirebaseDatabase.getInstance().getReference().child("users");

    }
}