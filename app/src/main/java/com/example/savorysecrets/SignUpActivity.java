package com.example.savorysecrets;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.savorysecrets.ui.home.HomeFragment;
import com.example.savorysecrets.R;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class SignUpActivity extends AppCompatActivity{

    Button sign_up_account;
    TextInputLayout email, fName, lName, pNumber, pword;

    FirebaseDatabase rootNode;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        sign_up_account = (Button) findViewById(R.id.sign_up_account);
        email = findViewById(R.id.emailAddress);
        fName = findViewById(R.id.firstName);
        lName = findViewById(R.id.lastName);
        pword = findViewById(R.id.password);
        pNumber = findViewById(R.id.phoneNumber);
        

        sign_up_account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                rootNode = FirebaseDatabase.getInstance();
                reference = rootNode.getReference("users");

                //Getting all the values
                String user_email = email.getEditText().getText().toString();
                String first_name = fName.getEditText().getText().toString();
                String last_name = lName.getEditText().getText().toString();
                String phone_number = pNumber.getEditText().getText().toString();
                String user_password = pword.getEditText().getText().toString();

                UserHelperClass helperClass = new UserHelperClass(user_email, first_name, last_name, phone_number, user_password);
                reference.child(phone_number).setValue(helperClass);
                openNavigationScreen();
            }
        });
    }

    private void openNavigationScreen() {
        Intent intent = new Intent(this, NavigationScreen.class);
        startActivity(intent);
    }
}