package com.freelance.rapaka.tvongo.model.beans;

import com.google.gson.annotations.SerializedName;

/**
 * Created Rakesh Rapaka on 04/04/2017.
 */
public class ClientContext {

    public Client getClientContext() {
        return clientContext;
    }

    public String getType() {
        return type;
    }

    public ClientContext(Client clientContext) {

        this.clientContext = clientContext;
        type = "APP";
    }
    @SerializedName("clientContext")
    Client clientContext;

    @SerializedName("type")
    String type;
}
