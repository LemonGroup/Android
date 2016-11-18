package com.slack.geekbrainswork.ai.data.local;

public class PreferencesDemo implements PrefHelper {

    private static PreferencesDemo preferences;
    private static String token = "sa4564asdas4f56af4asasewqjmwer24";

    private PreferencesDemo() {
    }

    public static PreferencesDemo getPreferences() {
        if (preferences == null) {
            preferences = new PreferencesDemo();
        }

        return preferences;
    }

    @Override
    public String getFromPref() {
        return token;
    }

    @Override
    public void writeToPref(String token) {
        PreferencesDemo.token = token;
    }

    @Override
    public void clear() {
        token = null;
    }
}
