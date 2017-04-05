package com.freelance.rapaka.tvongo.model.beans;

import com.google.gson.annotations.SerializedName;

/**
 * Created by Rakesh Rapaka on 04/04/2017.
 */
public class Client {


    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
    }

    public Client(String client) {
        this.client = client;
    }
    @SerializedName("client")
    String client;

}
