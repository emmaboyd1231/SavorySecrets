package com.example.savorysecrets;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class RecipeAdapter extends RecyclerView.Adapter <RecipeAdapter.RecipeViewHolder>{

    public Context mCtx;
    public List<RecipeHelperClass> recipeList;

    public RecipeAdapter(Context mCtx, List<RecipeHelperClass> recipeList) {
        this.mCtx = mCtx;
        this.recipeList = recipeList;
    }

    @NonNull
    @Override
    public RecipeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mCtx).inflate(R.layout.recipe_list_layout, null);
        return new RecipeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(RecipeViewHolder holder, int position) {
        RecipeHelperClass recipe = recipeList.get(position);

        /*holder.textViewTitle.setText(recipe.title);
        holder.textViewIngredients.setText(recipe.ingredients);
        holder.textViewStep1.setText(recipe.step1);
        holder.textViewStep2.setText(recipe.step2);
        holder.textViewStep3.setText(recipe.step3);
        holder.textViewStep4.setText(recipe.step4);
        holder.textViewStep5.setText(recipe.step5);
        holder.textViewInstructions.setText(recipe.instructions);*/

        holder.textViewUser.setText(recipe.getUser_username());
        holder.textViewTitle.setText(recipe.getTitle());
        holder.textViewIngredients.setText(recipe.getIngredients());
        holder.textViewStep1.setText(recipe.getStep1());
        holder.textViewStep2.setText(recipe.getStep2());
        holder.textViewStep3.setText(recipe.getStep3());
        holder.textViewStep4.setText(recipe.getStep4());
        holder.textViewStep5.setText(recipe.getStep5());
        holder.textViewInstructions.setText(recipe.getInstructions());

    }

    @Override
    public int getItemCount() {
        return recipeList.size();
    }

    class RecipeViewHolder extends RecyclerView.ViewHolder {

        TextView textViewUser, textViewTitle, textViewIngredients, textViewStep1, textViewStep2, textViewStep3, textViewStep4, textViewStep5, textViewInstructions;

        public RecipeViewHolder(View itemView) {
            super(itemView);

            textViewUser = itemView.findViewById(R.id.textViewUser);
            textViewTitle = itemView.findViewById(R.id.textViewTitle);
            textViewIngredients = itemView.findViewById(R.id.textViewIngredients);
            textViewStep1 = itemView.findViewById(R.id.textViewStep1);
            textViewStep2 = itemView.findViewById(R.id.textViewStep2);
            textViewStep3 = itemView.findViewById(R.id.textViewStep3);
            textViewStep4 = itemView.findViewById(R.id.textViewStep4);
            textViewStep5 = itemView.findViewById(R.id.textViewStep5);
            textViewInstructions = itemView.findViewById(R.id.textViewInstructions);
        }
    }

}
