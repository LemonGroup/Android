package com.slack.geekbrainswork.ai.data.local;

public interface PrefHelper {
    String getFromPref();

    void writeToPref(String token);

    void clear();
}
