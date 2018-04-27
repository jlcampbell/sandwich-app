package com.udacity.sandwichclub;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.udacity.sandwichclub.model.Sandwich;
import com.udacity.sandwichclub.utils.JsonUtils;

import org.json.JSONException;

import java.util.List;

public class DetailActivity extends AppCompatActivity {

    public static final String EXTRA_POSITION = "extra_position";
    private static final int DEFAULT_POSITION = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        ImageView ingredientsIv = findViewById(R.id.image_iv);

        Intent intent = getIntent();
        if (intent == null) {
            closeOnError();
        }

        int position = intent.getIntExtra(EXTRA_POSITION, DEFAULT_POSITION);
        if (position == DEFAULT_POSITION) {
            // EXTRA_POSITION not found in intent
            closeOnError();
            return;
        }

        String[] sandwiches = getResources().getStringArray(R.array.sandwich_details);
        String json = sandwiches[position];
        try {
            Sandwich sandwich = JsonUtils.parseSandwichJson(json);
            if (sandwich == null) {
                // Sandwich data unavailable
                closeOnError();
                return;
            }
            populateUI(sandwich);
            Picasso.with(this)
                    .load(sandwich.getImage())
                    .into(ingredientsIv);

            setTitle(sandwich.getMainName());


        } catch (JSONException e){

        }

    }

    private void closeOnError() {
        finish();
        Toast.makeText(this, R.string.detail_error_message, Toast.LENGTH_SHORT).show();
    }

    private void populateUI(Sandwich sandwich) {
        //TODO 6) don't show text views that have no content
        //TODO 7) don't show image view with no content
        //image and main name already done above

        //other names
        TextView otherNamesTV = findViewById(R.id.also_known_tv);
        List<String> namesList = sandwich.getAlsoKnownAs();
        String names = "";
        for (int i=0; i< namesList.size(); i++){
            names+= namesList.get(i);
            if (i < (namesList.size() - 1)) {
                names+= ", ";
            }
        }
        otherNamesTV.setText(names);

        //origin
        TextView originTV = findViewById(R.id.origin_tv);
        originTV.setText(sandwich.getPlaceOfOrigin());

        //description
        TextView descriptionTV = findViewById(R.id.description_tv);
        descriptionTV.setText(sandwich.getDescription());

        //ingredients
        TextView ingredientsTV = findViewById(R.id.ingredients_tv);
        List<String> ingredientsList = sandwich.getIngredients();
        String ingredientsString = "";
        for (int i=0; i< ingredientsList.size(); i++){
            ingredientsString+= ingredientsList.get(i);
            if (i<(ingredientsList.size()-1)) {
                ingredientsString+= ", ";
            }
        }
        ingredientsTV.setText(ingredientsString);
    }
}
