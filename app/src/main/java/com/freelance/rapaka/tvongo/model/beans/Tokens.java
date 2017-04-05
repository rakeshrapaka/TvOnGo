package com.freelance.rapaka.tvongo.model.beans;

import com.google.gson.annotations.SerializedName;

/**
 * Created by rra7y33 on 05/07/2016.
 */
public class Tokens {

    @SerializedName("access_token")
    String access_token;
    @SerializedName("refresh_token")
    String refresh_token;
    @SerializedName("expires_in")
    String expires_in;

    public String getAccessToken() {
        return access_token;
    }

    public void setAccessToken(String accessToken) {
        access_token = accessToken;
    }

    public String getRefreshToken() {
        return refresh_token;
    }

    public void setRefreshToken(String refreshToken) {
        refresh_token = refreshToken;
    }
    public String getExpiresIn() {
        return expires_in;
    }

    public void setExpiresIn(String expires_in) {
        this.expires_in = expires_in;
    }



}
