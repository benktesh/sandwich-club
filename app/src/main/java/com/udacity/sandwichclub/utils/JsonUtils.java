package com.udacity.sandwichclub.utils;

import android.support.annotation.NonNull;
import android.util.Log;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonUtils {

    private static final String TAG = JsonUtils.class.getSimpleName();
    public static Sandwich parseSandwichJson(String json) {
        try {


            Sandwich sandwich = new Sandwich();

            JSONObject object = new JSONObject(json);

            //name is complex object
            JSONObject nameObject = object.getJSONObject("name");
            sandwich.setMainName(nameObject.getString("mainName"));
            sandwich.setAlsoKnownAs(getStringsFromArray(nameObject.getString("alsoKnownAs")));

            sandwich.setPlaceOfOrigin(object.getString("placeOfOrigin"));
            sandwich.setDescription(object.getString("description"));
            sandwich.setImage(object.getString("image"));
            sandwich.setIngredients(getStringsFromArray(object.getString("ingredients")));

            return sandwich;
        }
        catch(Exception ex)
        {
            Log.e(TAG, "Could not parse json " + json);
            return null;
        }
    }



    /**
     * This method parses a json string to a list of string
     * @param - String stringArray representation of json array
     * @return List<String> list of string
     * @throws JSONException
     */
    @NonNull
    private static List<String> getStringsFromArray(String stringArray) throws JSONException {
        List<String> list = new ArrayList<>();
        JSONArray jsonarray = new JSONArray(stringArray);
        for(int i = 0; i < jsonarray.length(); i++) {
            list.add(jsonarray.getString(i));
        }
        return list;
    }
}
