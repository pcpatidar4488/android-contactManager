package com.example.berylsystems.apiusingservices.object_preference;

import android.content.Context;
import android.preference.PreferenceManager;

import com.example.berylsystems.apiusingservices.utils.Cv;
import com.google.gson.Gson;

/**
 * Created by BerylSystems on 19-Mar-18.
 */

public class LocalRepositories {
    public synchronized static void saveAppUser(Context ctx, AppUser user) {
        String jsonString = new Gson().toJson(user);
        PreferenceManager.getDefaultSharedPreferences(ctx)
                .edit()
                .putString(Cv.PREFS_APP_USER, jsonString)
                .commit();
    }

    public synchronized static AppUser getAppUser(Context ctx) {

        String jsonString = PreferenceManager.getDefaultSharedPreferences(ctx)
                .getString(Cv.PREFS_APP_USER, "");

        return "".equals(jsonString) ?
                null : new Gson().fromJson(jsonString, AppUser.class);
    }
}
