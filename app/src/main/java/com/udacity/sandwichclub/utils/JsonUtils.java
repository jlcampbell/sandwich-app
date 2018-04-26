package com.udacity.sandwichclub.utils;

import android.util.Log;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;

public class JsonUtils {
    private static final String TAG = "json utils";




    public static Sandwich parseSandwichJson(String json) throws JSONException {
            //TODO 4) handle exception properly
            final String SAND_NAME_MAIN = "mainName";
            final String SAND_NAME = "name";
            final String SAND_AKA = "alsoKnownAs";

            final String SAND_DESCRIPTION = "description";
            final String SAND_ORIGIN = "placeOfOrigin";
            final String SAND_IMAGE = "image";
            final String SAND_INGREDIENTS = "ingredients";


            JSONObject sandwichJson = new JSONObject(json);
            String origin = sandwichJson.getString(SAND_ORIGIN);
            String description = sandwichJson.getString(SAND_DESCRIPTION);
            String image = sandwichJson.getString(SAND_IMAGE);
            String ingredients = sandwichJson.getString(SAND_INGREDIENTS);

            JSONObject name = sandwichJson.getJSONObject(SAND_NAME);
            String mainName = name.getString(SAND_NAME_MAIN);
            //String otherNames = name.getString(SAND_AKA);

            JSONArray otherNamesArray = name.getJSONArray(SAND_AKA);
            List<String> otherNamesList = new ArrayList<String>();
            Log.v(TAG, "othernamesarray length = " + String.valueOf(otherNamesArray.length()));
            for (int i = 0; i < otherNamesArray.length(); i++){
                    otherNamesList.add(otherNamesArray.getString(i));
                    int size = otherNamesList.size();
                    Log.v(TAG, "how many names there are "+String.valueOf(size));
            }

            JSONArray ingredientsArray = sandwichJson.getJSONArray(SAND_INGREDIENTS);
            List<String> ingredientsList = new ArrayList<String>();
            for (int i=0; i < ingredientsArray.length(); i++){
                ingredientsList.add(ingredientsArray.getString(i));
            }



            Log.v(TAG, origin);
            Log.v(TAG, description);
            Log.v(TAG, image);
            Log.v(TAG, mainName);

            if (otherNamesList.size()>0){
                Log.v(TAG, otherNamesList.get(0));
            }

            //Log.v(TAG, ingredients);
            Log.v(TAG, ingredientsList.get(0));

        Sandwich mSandwich = new Sandwich();


        mSandwich.setDescription(description);
        mSandwich.setImage(image);

        //List<String> aka = Arrays.asList("name1", "name2");
        mSandwich.setAlsoKnownAs(otherNamesList);

        //List<String> ingreds = Arrays.asList("bread", "cheese");
        mSandwich.setIngredients(ingredientsList);

        mSandwich.setMainName(mainName);
        mSandwich.setPlaceOfOrigin(origin);


        return mSandwich;
    }
}
