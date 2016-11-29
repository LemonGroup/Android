package com.slack.geekbrainswork.ai.data.dto;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class IsBusyResponse {

<<<<<<< HEAD
    @SerializedName("isBusy")
    @Expose
    private Boolean isBusy;

    /**
     * @return The isBusy
     */
    public Boolean getIsBusy() {
        return isBusy;
    }

    /**
     * @param isBusy The isBusy
     */
    public void setIsBusy(Boolean isBusy) {
        this.isBusy = isBusy;
=======
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
>>>>>>> origin/master
    }
}
