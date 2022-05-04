package com.example.savorysecrets.ui.login;

import androidx.annotation.NonNull;
import androidx.lifecycle.ViewModelProvider;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.StringRes;
import androidx.appcompat.app.AppCompatActivity;

import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.savorysecrets.NavigationScreen;
import com.example.savorysecrets.R;
import com.example.savorysecrets.SignUpActivity;
import com.example.savorysecrets.UserHelperClass;
import com.example.savorysecrets.databinding.ActivityLoginBinding;

import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.android.gms.tasks.OnCompleteListener;

public class LoginActivity extends AppCompatActivity {
    private static final String TAG = "EmailPassword";
    private LoginViewModel loginViewModel;
    private ActivityLoginBinding binding;
    private FirebaseAuth mAuth;
    //private Button loginButton;

    TextInputLayout email, pword;
    Button login, sign_up;
    public String currentUser;

    FirebaseDatabase rootNode;
    DatabaseReference reference;
    //FirebaseAuth fAuth;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityLoginBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        rootNode = FirebaseDatabase.getInstance();
        reference = rootNode.getReference("users");

        email = findViewById(R.id.emailAddress);
        pword = findViewById(R.id.password);

        login = (Button) findViewById(R.id.login);

        loginViewModel = new ViewModelProvider(this, new LoginViewModelFactory())
                .get(LoginViewModel.class);

        //final EditText usernameEditText = binding.username;
        //final EditText passwordEditText = binding.password;
        //final Button loginButton = binding.login;
        //final ProgressBar loadingProgressBar = binding.loading;

        mAuth = FirebaseAuth.getInstance();

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //loadingProgressBar.setVisibility(View.VISIBLE);

                String user_email = email.getEditText().getText().toString();
                String user_password = pword.getEditText().getText().toString();

                signIn(user_email, user_password);
                //signInWithEmailandPassword(mAuth, username.getEditText().getText().toString(), password.getEditText().getText().toString());
            }
        });

        sign_up = (Button) findViewById(R.id.sign_up);
        sign_up.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openSignUpActivity();
            }
        });

    }

    private void signIn(String email, String pword) {
        // [START sign_in_with_email]
        mAuth.signInWithEmailAndPassword(email, pword)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithEmail:success");
                            Toast.makeText(LoginActivity.this, "Success! Authentication succeeded.",
                                    Toast.LENGTH_SHORT).show();
                            FirebaseUser current_user = mAuth.getCurrentUser();
                            updateUI(current_user);
                            openNavigationScreen();
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithEmail:failure", task.getException());
                            Toast.makeText(LoginActivity.this, "Error! User authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            updateUI(null);
                        }
                    }
                });
        // [END sign_in_with_email]
    }

    /*private void signIn(final String user, final String pword){
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                //fAuth = FirebaseAuth.getInstance();
                String userID = fAuth.getCurrentUser().getUid();
                if(snapshot.child(userID).exists()){
                    if(!user.isEmpty()){
                        UserHelperClass log = snapshot.child(userID).getValue(UserHelperClass.class);
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

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }*/

    public void signInWithEmailandPassword(FirebaseAuth mAuth, String user, String pword){
        return;
    }

    public void openNavigationScreen(){
        Intent intent3 = new Intent(this, NavigationScreen.class);
        startActivity(intent3);
    }

    public void openSignUpActivity(){
        Intent intent4 = new Intent(this, SignUpActivity.class);
        startActivity(intent4);
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

    private void updateUI(FirebaseUser user) {

    }

}