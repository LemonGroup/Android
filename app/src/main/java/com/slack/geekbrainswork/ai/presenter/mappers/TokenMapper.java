package com.slack.geekbrainswork.ai.presenter.mappers;

import com.slack.geekbrainswork.ai.data.dto.TokenResponse;

import rx.functions.Func1;

public class TokenMapper implements Func1<TokenResponse, String> {
    @Override
    public String call(TokenResponse tokenResponse) {
        return tokenResponse.getToken();
    }
}
