package com.slack.geekbrainswork.ai.data.dto;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class IsBusyResponse {

    @SerializedName("usernameIsBusy")
    @Expose
    private Boolean usernameIsBusy;
    @SerializedName("emailIsBusy")
    @Expose
    private Boolean emailIsBusy;

    /**
     * @return The usernameIsBusy
     */
    public Boolean getUsernameIsBusy() {
        return usernameIsBusy;
    }

    /**
     * @param usernameIsBusy The usernameIsBusy
     */
    public void setUsernameIsBusy(Boolean usernameIsBusy) {
        this.usernameIsBusy = usernameIsBusy;
    }

    public Boolean getEmailIsBusy() {
        return emailIsBusy;
    }

    public void setEmailIsBusy(Boolean emailIsBusy) {
        this.emailIsBusy = emailIsBusy;
    }
}