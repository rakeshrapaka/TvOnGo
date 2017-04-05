package com.freelance.rapaka.tvongo.util;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import be.colruyt.xtra.XtraApp;


public class ConnectivityReceiver {
 
    public static boolean isConnected() {
        ConnectivityManager
                cm = (ConnectivityManager) XtraApp.getInstance().getApplicationContext()
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        return activeNetwork != null
                && activeNetwork.isConnectedOrConnecting();
    }

}