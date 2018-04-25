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




    public static Sandwich parseSandwichJson(String json)
    throws JSONException {

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
            String otherNames = name.getString(SAND_AKA);

            JSONArray otherNamesArray = name.getJSONArray(SAND_AKA);
            //need to convert to string array from list string
            List<String> otherNamesList = new ArrayList<String>();
            for (int i = 0; i < otherNamesArray.length(); i++){
                    otherNamesList.add(otherNamesArray.getString(i));
            }



            String sampleOtherName = otherNamesArray.getString(0);
            //String name = sandwichJson.getString(SAND_NAME_MAIN);
            Log.v(TAG, origin);
            Log.v(TAG, description);
            Log.v(TAG, image);
            Log.v(TAG, mainName);
            Log.v(TAG, otherNamesList[0]);
            Log.v(TAG, sampleOtherName);
            Log.v(TAG, ingredients);


        Sandwich mSandwich = new Sandwich();


        //mSandwich.setDescription("a good sandwich");
        //mSandwich.setImage("https://upload.wikimedia.org/wikipedia/commons/c/ca/Bosna_mit_2_Bratw%C3%BCrsten.jpg");

        //List<String> aka = Arrays.asList("name1", "name2");
        //mSandwich.setAlsoKnownAs(aka);

        //List<String> ingreds = Arrays.asList("bread", "cheese");
        //mSandwich.setIngredients(ingreds);

        //mSandwich.setMainName("special sammy");
        //mSandwich.setPlaceOfOrigin("ann arbor");


        return mSandwich;
    }
}
