package com.slack.geekbrainswork.ai.data.dto;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class IsBusyResponse {

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
    }
}
