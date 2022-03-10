package com.example.savorysecrets;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.savorysecrets.ui.home.HomeFragment;
import com.example.savorysecrets.R;

public class SignUpActivity extends AppCompatActivity{

    private Button sign_up_account;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        sign_up_account = (Button) findViewById(R.id.sign_up_account);
        sign_up_account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openNavigationScreen();
            }
        });
    }

    private void openNavigationScreen() {
        Intent intent = new Intent(this, NavigationScreen.class);
        startActivity(intent);
    }
}