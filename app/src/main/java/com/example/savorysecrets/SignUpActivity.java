package com.example.savorysecrets;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.savorysecrets.ui.home.HomeFragment;

public class SignUpActivity extends AppCompatActivity implements View.OnClickListener{

    private Button sign_up_account;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);
        sign_up_account = (Button) findViewById(R.id.sign_up_account);
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent(this, HomeFragment.class);
        startActivity(intent);
    }
}