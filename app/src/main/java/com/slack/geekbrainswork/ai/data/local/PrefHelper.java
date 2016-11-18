package com.slack.geekbrainswork.ai.data.local;

public interface PrefHelper {
    String getFromPref();

    String writeToPref(String token);

    void clear();
}
