package com.udacity.sandwichclub.utils;

import android.util.Log;

import com.udacity.sandwichclub.model.Sandwich;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class JsonUtils {
    private static final String TAG = "json utils";

    public static Sandwich parseSandwichJson(String json) {

        String test = json.replace("\\", "");
        Log.v(TAG, test);


        Sandwich mSandwich = new Sandwich();
        mSandwich.setDescription("a good sandwich");
        mSandwich.setImage("https://upload.wikimedia.org/wikipedia/commons/c/ca/Bosna_mit_2_Bratw%C3%BCrsten.jpg");

        List<String> aka = Arrays.asList("name1", "name2");
        mSandwich.setAlsoKnownAs(aka);

        List<String> ingreds = Arrays.asList("bread", "cheese");
        mSandwich.setIngredients(ingreds);

        mSandwich.setMainName("special sammy");
        mSandwich.setPlaceOfOrigin("ann arbor");


        return mSandwich;
    }
}
