package com.example.savorysecrets.ui.login;

import android.app.Activity;

import androidx.annotation.NonNull;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.appcompat.app.AppCompatActivity;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.savorysecrets.NavigationScreen;
import com.example.savorysecrets.R;
import com.example.savorysecrets.UserHelperClass;
import com.example.savorysecrets.ui.home.HomeFragment;
import com.example.savorysecrets.ui.login.LoginViewModel;
import com.example.savorysecrets.ui.login.LoginViewModelFactory;
import com.example.savorysecrets.databinding.ActivityLoginBinding;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.ktx.Firebase;

public class LoginActivity extends AppCompatActivity {

    private LoginViewModel loginViewModel;
    private ActivityLoginBinding binding;
    private FirebaseAuth mAuth;
    //private Button loginButton;

    TextInputLayout username, password;
    Button login;
    public String currentUser;

    FirebaseDatabase rootNode;
    DatabaseReference reference;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        rootNode = FirebaseDatabase.getInstance();
        reference = rootNode.getReference("users");

        username = (TextInputLayout) findViewById(R.id.username);
        password = (TextInputLayout) findViewById(R.id.password);

        login = (Button) findViewById(R.id.login);

        loginViewModel = new ViewModelProvider(this, new LoginViewModelFactory())
                .get(LoginViewModel.class);

        //final EditText usernameEditText = binding.username;
        //final EditText passwordEditText = binding.password;
        //final Button loginButton = binding.login;
        final ProgressBar loadingProgressBar = binding.loading;

        //mAuth = FirebaseDatabase.getInstance();

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loadingProgressBar.setVisibility(View.VISIBLE);
                signIn(username.getEditText().getText().toString(),
                        password.getEditText().getText().toString());
            }
        });
    }

    private void signIn(final String user, final String pword){
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                if(snapshot.child(user).exists()){
                    if(!user.isEmpty()){
                        UserHelperClass log = snapshot.child(user).getValue(UserHelperClass.class);
                        if(log.getUser_password().equals(pword)){
                            Toast.makeText(LoginActivity.this, "Successful Login!", Toast.LENGTH_SHORT).show();
                            currentUser=user;
                            openNavigationScreen();
                            DatabaseReference login_ref = reference.child(currentUser);
                        }
                        else{
                            Toast.makeText(LoginActivity.this, "Unsuccessful Login.", Toast.LENGTH_SHORT).show();
                        }
                    }
                    else {
                        Toast.makeText(LoginActivity.this, "Email is not registered.", Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    public void openNavigationScreen(){
        Intent intent3 = new Intent(this, NavigationScreen.class);
        startActivity(intent3);
    }

    private void updateUiWithUser(LoggedInUserView model) {
        String welcome = getString(R.string.welcome);
        //String welcome = getString(R.string.welcome) + model.getDisplayName();
        // TODO : initiate successful logged in experience
        Toast.makeText(getApplicationContext(), welcome, Toast.LENGTH_LONG).show();
    }

    private void showLoginFailed(@StringRes Integer errorString) {
        Toast.makeText(getApplicationContext(), errorString, Toast.LENGTH_SHORT).show();
    }
}