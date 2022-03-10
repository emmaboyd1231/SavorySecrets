package com.example.savorysecrets;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class AddRecipe extends AppCompatActivity {

    private Button add_recipe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_recipe);

        add_recipe = (android.widget.Button) findViewById(R.id.add_recipe);
        add_recipe.setOnClickListener(new View.OnClickListener() {
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