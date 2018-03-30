package com.example.berylsystems.apiusingservices.shared_preference;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by BerylSystems on 20-Mar-18.
 */

public class Preferences {
    Context _context;
    SharedPreferences pref;
    SharedPreferences.Editor editor;
    private static Preferences instance;
    private static final String PREF_NAME = "ApiUsingServices";
    int PRIVATE_MODE = 0;
    private static final String IS_LOGIN = "Login";


    private Preferences(Context context) {
        this._context = context;
        pref = _context.getSharedPreferences(PREF_NAME, PRIVATE_MODE);
        editor = pref.edit();
    }

    public static Preferences getInstance(Context context) {
        if (instance == null) {
            instance = new Preferences(context);
        }
        return instance;
    }


    public void setLogin(Boolean isLogin) {
        editor.putBoolean(IS_LOGIN, isLogin);
        editor.commit();
    }

    public Boolean getLogin() {
        return pref.getBoolean(IS_LOGIN, false);
    }


}
