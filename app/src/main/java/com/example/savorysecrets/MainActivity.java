package com.example.savorysecrets;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.savorysecrets.ui.login.LoginActivity;

public class MainActivity extends AppCompatActivity {
    private Button log_in;
    private Button sign_up;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        log_in = (Button) findViewById(R.id.log_in);
        log_in.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openLoginActivity();
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

    public void openLoginActivity(){
        Intent intent1 = new Intent(this, LoginActivity.class);
        startActivity(intent1);
    }

    public void openSignUpActivity(){
        Intent intent2 = new Intent(this, SignUpActivity.class);
        startActivity(intent2);
    }
}