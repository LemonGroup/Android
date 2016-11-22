package com.slack.geekbrainswork.ai.data.local;

import android.content.Context;
import android.content.SharedPreferences;

import rx.Observable;

public class PreferencesHelper implements PrefHelper {
    private static final String PREF_FILE_NAME = "lemon_stats_pref_file";
    private static final String PREF_TOKEN_NAME = "lemon_token";

    private final SharedPreferences preferences;

    public PreferencesHelper(Context context) {
        preferences = context.getSharedPreferences(PREF_FILE_NAME, Context.MODE_PRIVATE);
    }

    public String getFromPref() {
        return preferences.getString(PREF_TOKEN_NAME, null);
    }

    public String writeToPref(String token) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(PREF_TOKEN_NAME, token);
        editor.apply();
        return getFromPref();
    }

    public void clear() {
        preferences.edit().clear().apply();
    }
}
