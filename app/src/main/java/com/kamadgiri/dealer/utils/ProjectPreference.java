package com.kamadgiri.dealer.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.kamadgiri.dealer.BuildConfig;


/**
 * Created by Anand on 27-04-2016.
 */
public class ProjectPreference {
    private static ProjectPreference preference;


    public static String getSharedPreferenceData(String contentPrefName, Context context){
        try {
            SharedPreferences prefs = context.getSharedPreferences(BuildConfig.APPLICATION_ID ,
                    Context.MODE_PRIVATE);
            String str = prefs.getString(contentPrefName, "");
            return str;
        } catch (Exception e) {

        }
        return null;
    }
// method for save shared preference data
    public static void saveSharedPreferenceData(String contentPrefName, String content, Context context){
        try {
            SharedPreferences prefs = context.getSharedPreferences(BuildConfig.APPLICATION_ID ,
                    Context.MODE_PRIVATE);

            SharedPreferences.Editor prefEditor = prefs.edit();
            prefEditor.putString(contentPrefName, content);

            prefEditor.commit();
            prefEditor.clear();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
// method for remove shared preference data
    public static void removeSharedPreferenceData(String contentPrefName, Context context){
        try {
            SharedPreferences prefs = context.getSharedPreferences(BuildConfig.APPLICATION_ID ,
                    Context.MODE_PRIVATE);
            SharedPreferences.Editor prefEditor = prefs.edit();
            prefEditor.remove(contentPrefName);
            prefEditor.commit();
            prefEditor.clear();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
