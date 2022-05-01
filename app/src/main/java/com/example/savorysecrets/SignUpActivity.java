package com.example.savorysecrets;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.savorysecrets.ui.home.HomeFragment;
import com.example.savorysecrets.R;
import com.example.savorysecrets.ui.login.LoginActivity;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
//import com.google.firebase.auth.AuthResult;
//import com.google.firebase.auth.FirebaseAuth;

public class SignUpActivity extends AppCompatActivity{

    Button sign_up_account;
    TextInputLayout email, user_name, fName, lName, pNumber, pword;
    private EditText inputUsername, inputPassword;
    public String currentUser;

    FirebaseDatabase rootNode;
    DatabaseReference reference;
    //private FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        //auth = FirebaseAuth.getInstance();

        sign_up_account = (Button) findViewById(R.id.sign_up_account);
        email = findViewById(R.id.emailAddress);
        user_name = findViewById(R.id.username);
        fName = findViewById(R.id.firstName);
        lName = findViewById(R.id.lastName);
        pword = findViewById(R.id.password);
        pNumber = findViewById(R.id.phoneNumber);

        //EditUsername = (EditText) findViewById(R.id.username);
        //EditPassword = (EditText) findViewById(R.id.password);

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

                UserHelperClass helperClass = new UserHelperClass(user_email, user_username, first_name, last_name, phone_number, user_password);
                reference.child(user_username).setValue(helperClass);

                /*if(!validateEmail() | !validateUsername() | !validateFirstName() | !validateLastName() | !validatePhoneNumber() | !validatePassword()){
                    return;
                }
                else{
                    openNavigationScreen();
                }*/

                if(validateEmail() == true && validateUsername() == true && validateFirstName() == true && validateLastName() == true && validatePhoneNumber() == true && validatePassword() == true){
                    currentUser= user_username;
                    openLoginActivity();
                }

            }
        });
    }

    private Boolean validateEmail(){
        String val = email.getEditText().getText().toString();
        String emailPattern = "[a-zA-Z0-9._]+@[a-z]+\\.+[a-z]+";

        if(val.isEmpty()){
            email.setError("Field cannot be empty");
            return false;
        }
        else if(!val.matches(emailPattern)){
            email.setError("Invalid email address");
            return false;
        }
        else{
            email.setError(null);
            return true;
        }
    }

    private Boolean validateUsername(){
        String val = user_name.getEditText().getText().toString();

        if(val.isEmpty()){
            user_name.setError("Field cannot be empty");
            return false;
        }
        else{
            email.setError(null);
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
            return true;
        }
    }

    private Boolean validatePassword(){
        String val = pword.getEditText().getText().toString();
        String passwordVal = "^" +
                "(?=.*[a-zA-Z])" + // any letter
                "(?=.*[@#$%^&+=])" + //at least one special character
                "(?=\\S$)" + //no white spaces
                ".(6,)" + //at least 6 characters
                "$";
        if(val.isEmpty()){
            pword.setError("Field cannot be empty");
            return false;
        }
        else if(!val.matches(passwordVal)){
            pword.setError("Password is too weak");
            return false;
        }
        else{
            pword.setError(null);
            return true;
        }
    }

    /*public void registerUser (View view){
        if(!validateEmail() | !validateUsername() | !validateFirstName() | !validateLastName() | !validatePhoneNumber() | !validatePassword()){
            return;
        }
        else{
            openNavigationScreen();
        }

        //Getting all the values
        String user_email = email.getEditText().getText().toString();
        String first_name = fName.getEditText().getText().toString();
        String last_name = lName.getEditText().getText().toString();
        String phone_number = pNumber.getEditText().getText().toString();
        String user_password = pword.getEditText().getText().toString();
        String user_username = user_name.getEditText().getText().toString();


        //UserHelperClass helperClass = new UserHelperClass(user_email, user_username, first_name, last_name, phone_number, user_password);
        //reference.child(phone_number).setValue(helperClass);
        //openNavigationScreen();
    }*/

    private void openLoginActivity() {
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }
}