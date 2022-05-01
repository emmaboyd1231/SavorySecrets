package com.example.savorysecrets;

import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.savorysecrets.ui.login.LoginActivity;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MyRecipes extends AppCompatActivity {

    RecyclerView recyclerView;
    RecipeAdapter adapter;
    List<RecipeHelperClass> recipeList;

    DatabaseReference dbRecipes;

    LoginActivity login_user = new LoginActivity();
    String currentUser = login_user.currentUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_recipes);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        recipeList = new ArrayList<>();

        adapter = new RecipeAdapter(this, recipeList);
        recyclerView.setAdapter(adapter);

        /*recipeList.add(
                new RecipeHelperClass(
                        "emboyd31", "Pizza", "Ingredients Example", "Step 1 Example", "Step 2 Example", "Step 3 Example", "Step 4 Example", "Step 5 Example", "Instructions Example"));

        recipeList.add(
                new RecipeHelperClass(
                        "ggrevera", "Pizza", "Ingredients Example", "Step 1 Example", "Step 2 Example", "Step 3 Example", "Step 4 Example", "Step 5 Example", "Instructions Example"));*/

        //dbRecipes = FirebaseDatabase.getInstance().getReference("recipes");

        Query query = FirebaseDatabase.getInstance().getReference("recipes")
                .orderByChild("user_username")
                .equalTo("emboyd31");
        query.addListenerForSingleValueEvent(valueEventListener);
    }

    ValueEventListener valueEventListener = new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot snapshot) {
            recipeList.clear();
            if(snapshot.exists()){
                for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                    RecipeHelperClass recipe = snapshot.getValue(RecipeHelperClass.class);
                    recipeList.add(recipe);
                }
                adapter.notifyDataSetChanged();
            }
        }

        @Override
        public void onCancelled(@NonNull DatabaseError error) {

        }
    };

}