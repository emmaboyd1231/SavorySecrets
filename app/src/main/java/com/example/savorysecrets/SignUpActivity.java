package com.example.savorysecrets;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.savorysecrets.ui.home.HomeFragment;
import com.example.savorysecrets.R;
import com.example.savorysecrets.ui.login.LoginActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class SignUpActivity extends AppCompatActivity{
    public static final String TAG = "TAG";
    Button sign_up_account, login;
    TextInputLayout email, user_name, fName, lName, pNumber, pword;
    private EditText inputUsername, inputPassword;
    public String currentUser;

    FirebaseDatabase rootNode;
    DatabaseReference reference;
    FirebaseAuth fAuth;
    ProgressBar progressBar;
    FirebaseFirestore fStore;
    String userID;
    UserHelperClass helperClass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        // Initialize Firebase Auth
        //mAuth = FirebaseAuth.getInstance();

        sign_up_account = (Button) findViewById(R.id.sign_up_account);
        email = findViewById(R.id.emailAddress);
        user_name = findViewById(R.id.username);
        fName = findViewById(R.id.firstName);
        lName = findViewById(R.id.lastName);
        pword = findViewById(R.id.password);
        pNumber = findViewById(R.id.phoneNumber);

        fAuth = FirebaseAuth.getInstance();
        fStore = FirebaseFirestore.getInstance();
        //progressBar = findViewById(R.id.progressBar);

        sign_up_account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                rootNode = FirebaseDatabase.getInstance();
                reference = rootNode.getReference("users");

                //Getting all the values
                String user_email = email.getEditText().getText().toString();
                String user_username = user_name.getEditText().getText().toString();
                String first_name = fName.getEditText().getText().toString();
                String last_name = lName.getEditText().getText().toString();
                String phone_number = pNumber.getEditText().getText().toString();
                String user_password = pword.getEditText().getText().toString();

                FirebaseUser fuser = fAuth.getInstance().getCurrentUser();

               /* if(!validateEmail() | !validateUsername() | !validateFirstName() | !validateLastName() | !validatePhoneNumber() | !validatePassword()){
                    return;
                }
                else{
                    helperClass = new UserHelperClass(user_email, user_username, first_name, last_name, phone_number, user_password);
                    reference.child(userID).setValue(helperClass);
                    currentUser= user_username;
                    openLoginActivity();
                }*/

                if(validateEmail() && validateUsername() && validateFirstName() && validateLastName() && validatePhoneNumber() && validatePassword()) {
                    fAuth.createUserWithEmailAndPassword(user_email, user_password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            //if((task.isSuccessful()) && (validateEmail() && validateUsername() && validateFirstName() && validateLastName() && validatePhoneNumber() && validatePassword())) {
                            if (task.isSuccessful()) {
                                userID = fAuth.getCurrentUser().getUid();
                                helperClass = new UserHelperClass(user_email, user_username, first_name, last_name, phone_number, user_password);
                                reference.child(userID).setValue(helperClass);
                                Toast.makeText(SignUpActivity.this, "Success! User Created.", Toast.LENGTH_SHORT).show();
                                DocumentReference documentReference = fStore.collection("users").document(userID);
                                Map<String, Object> user = new HashMap<>();
                                user.put("fName", first_name);
                                user.put("lName", last_name);
                                user.put("phone", phone_number);
                                user.put("email", user_email);
                                user.put("password", user_password);
                                user.put("username", user_username);
                                openLoginActivity();

                            /*documentReference.set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    Log.d(TAG, "onSuccess: user Profile is created for "+ userID);
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Log.d(TAG, "onFailure: " + e.toString());
                                }
                            });*/
                                //startActivity(new Intent(getApplicationContext(),MainActivity.class));
                            } else {
                                //Toast.makeText(SignUpActivity.this, "Error ! " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                Toast.makeText(SignUpActivity.this, "Error! User not Created.", Toast.LENGTH_SHORT).show();
                                //progressBar.setVisibility(View.GONE);
                            }
                        }
                    });
                }

            }
        });

        login = (Button) findViewById(R.id.login);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openLoginActivity();
            }
        });
    }

    private Boolean validateEmail(){
        String val = email.getEditText().getText().toString();
        String emailPattern = "[a-zA-Z0-9\\+\\.\\_\\%\\-\\+]{1,256}" +
                "\\@" +
                "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,64}" +
                "(" +
                "\\." +
                "[a-zA-Z0-9][a-zA-Z0-9\\-]{0,25}" +
                ")+";

        if(val.isEmpty()){
            email.setError("Field cannot be empty");
            return false;
        }
        else if(!val.matches(emailPattern)){
            email.setError("Invalid email address, no @ sign");
            return false;
        }
        else{
            email.setError(null);
            return true;
        }
    }

    private Boolean validateUsername(){
        String val = user_name.getEditText().getText().toString();
        String noWhiteSpace = "\\A\\w{4,20}\\z";

        if (val.isEmpty()) {
            user_name.setError("Field cannot be empty");
            return false;
        } else if (val.length() >= 15) {
            user_name.setError("Username too long");
            return false;
        } else if (!val.matches(noWhiteSpace)) {
            user_name.setError("White Spaces are not allowed");
            return false;
        } else {
            user_name.setError(null);
            user_name.setErrorEnabled(false);
            return true;
        }
    }

    private Boolean validateFirstName(){
        String val = fName.getEditText().getText().toString();

        if(val.isEmpty()){
            fName.setError("Field cannot be empty");
            return false;
        }
        else{
            fName.setError(null);
            fName.setErrorEnabled(false);
            return true;
        }
    }

    private Boolean validateLastName(){
        String val = lName.getEditText().getText().toString();

        if(val.isEmpty()){
            lName.setError("Field cannot be empty");
            return false;
        }
        else{
            lName.setError(null);
            lName.setErrorEnabled(false);
            return true;
        }
    }

    private Boolean validatePhoneNumber(){
        String val = pNumber.getEditText().getText().toString();

        if(val.isEmpty()){
            pNumber.setError("Field cannot be empty");
            return false;
        }
        else{
            pNumber.setError(null);
            pNumber.setErrorEnabled(false);
            return true;
        }
    }

    private Boolean validatePassword(){
        String val = pword.getEditText().getText().toString();
        String passwordVal =  "^" +
                "(?=.*[0-9])" +         //at least 1 digit
                "(?=.*[a-z])" +         //at least 1 lower case letter
                "(?=.*[A-Z])" +         //at least 1 upper case letter
                "(?=.*[a-zA-Z])" +      //any letter
                "(?=.*[@#$%^&+=])" +    //at least 1 special character
                "(?=\\S+$)" +           //no white spaces
                ".{6,}" +               //at least 6 characters
                "$";
        if(val.isEmpty()){
            pword.setError("Field cannot be empty");
            return false;
        }
        else if(!val.matches(passwordVal)){
            pword.setError("Password is too weak. Password must include 1 digit, 1 lower case letter, 1 uppercase letter, one special character, and at least 6 characters.");
            return false;
        }
        else{
            pword.setError(null);
            return true;
        }
    }

    private void openLoginActivity() {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }



    public String getCurrentUser() {
        return currentUser;
    }
}