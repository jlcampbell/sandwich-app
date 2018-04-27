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


    /** Uses aJSONObject to parse a json string and return a Sandwich **/
    public static Sandwich parseSandwichJson(String json) throws JSONException {
        final String SAND_NAME_MAIN = "mainName";
        final String SAND_NAME = "name";
        final String SAND_AKA = "alsoKnownAs";

        final String SAND_DESCRIPTION = "description";
        final String SAND_ORIGIN = "placeOfOrigin";
        final String SAND_IMAGE = "image";
        final String SAND_INGREDIENTS = "ingredients";

        JSONObject mSandwichJson = new JSONObject(json);
        String mOrigin = mSandwichJson.getString(SAND_ORIGIN);
        String mDescription = mSandwichJson.getString(SAND_DESCRIPTION);
        String mImage = mSandwichJson.getString(SAND_IMAGE);

        JSONObject name = mSandwichJson.getJSONObject(SAND_NAME);
        String mMainName = name.getString(SAND_NAME_MAIN);

        JSONArray mOtherNamesArray = name.getJSONArray(SAND_AKA);
        List<String> mOtherNamesList = new ArrayList<String>();
        for (int i = 0; i < mOtherNamesArray.length(); i++){
                mOtherNamesList.add(mOtherNamesArray.getString(i));
                int size = mOtherNamesList.size();
                Log.v(TAG, "how many names there are "+String.valueOf(size));
        }

        JSONArray mIngredientsArray = mSandwichJson.getJSONArray(SAND_INGREDIENTS);
        List<String> mIngredientsList = new ArrayList<String>();
        for (int i=0; i < mIngredientsArray.length(); i++){
            mIngredientsList.add(mIngredientsArray.getString(i));
        }

        Sandwich mSandwich = new Sandwich();

        mSandwich.setDescription(mDescription);
        mSandwich.setImage(mImage);
        mSandwich.setAlsoKnownAs(mOtherNamesList);
        mSandwich.setIngredients(mIngredientsList);
        mSandwich.setMainName(mMainName);
        mSandwich.setPlaceOfOrigin(mOrigin);
        return mSandwich;
    }
}
