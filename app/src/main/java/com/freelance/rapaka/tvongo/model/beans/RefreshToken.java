package com.freelance.rapaka.tvongo.model.beans;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Rakesh Rapaka on 04/04/2017.
 */
public class RefreshToken {

    @SerializedName("clientContext")
    Client clientContext;

    @SerializedName("refresh_token")
    String refresh_token;

    public Client getClientContext() {
        return clientContext;
    }

    public String getRefresh_token() {
        return refresh_token;
    }

    public RefreshToken(Client clientContext, String refresh_token) {
        this.clientContext = clientContext;
        this.refresh_token = refresh_token;
    }

}
