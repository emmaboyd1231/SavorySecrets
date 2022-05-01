package com.example.savorysecrets;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.google.android.material.textfield.TextInputLayout;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddRecipe extends AppCompatActivity {

    private Button add_recipe;
    TextInputLayout user_name, r_title, r_ingredients, r_step1, r_step2, r_step3, r_step4, r_step5, r_instructions;

    FirebaseDatabase rootNode;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_recipe);

        add_recipe = (android.widget.Button) findViewById(R.id.add_recipe);

        user_name = findViewById(R.id.username);
        r_title = findViewById(R.id.recipe_title);
        r_ingredients = findViewById(R.id.recipe_ingredients);
        r_step1 = findViewById(R.id.recipe_step1);
        r_step2 = findViewById(R.id.recipe_step2);
        r_step3 = findViewById(R.id.recipe_step3);
        r_step4 = findViewById(R.id.recipe_step4);
        r_step5 = findViewById(R.id.recipe_step5);
        r_instructions = findViewById(R.id.recipe_cooking);

        add_recipe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                rootNode = FirebaseDatabase.getInstance();
                reference = rootNode.getReference("recipes");

                addRecipe();
                openNavigationScreen();
            }
        });
    }

    private void addRecipe(){
        //Getting all the values
        String user_username = user_name.getEditText().getText().toString();
        String title = r_title.getEditText().getText().toString();
        String ingredients = r_ingredients.getEditText().getText().toString();
        String step1 = r_step1.getEditText().getText().toString();
        String step2 = r_step2.getEditText().getText().toString();
        String step3 = r_step3.getEditText().getText().toString();
        String step4 = r_step4.getEditText().getText().toString();
        String step5 = r_step5.getEditText().getText().toString();
        String instructions = r_instructions.getEditText().getText().toString();

        String id = reference.push().getKey();
        RecipeHelperClass helperClass2 = new RecipeHelperClass(user_username, title, ingredients, step1, step2, step3, step4, step5, instructions);
        reference.child(id).setValue(helperClass2);
    }

    private void openNavigationScreen() {
        Intent intent = new Intent(this, NavigationScreen.class);
        startActivity(intent);
    }
}
