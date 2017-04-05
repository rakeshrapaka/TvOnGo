package com.freelance.rapaka.tvongo.helper;

import android.content.Context;
import android.util.Log;

import com.freelance.rapaka.tvongo.TvOnGo;
import com.freelance.rapaka.tvongo.util.Constants;

/**
 * Created by Rakesh Rapaka on 04/04/2017.
 */
public class TokenHelper {



    public static final boolean ENCRYPTION = true;

    TvOnGo appInstance;

    static TokenHelper tokenHelper;

    /**
     * Provides instance of the Token helper to
     * @return
     */
    public static TokenHelper getInstance(){
        if(tokenHelper==null){
            tokenHelper = new TokenHelper();
        }
        return tokenHelper;
    }

    /**
     * Private constructor
     */
    private TokenHelper(){
        this.appInstance = TvOnGo.getInstance();
    }

    /**
     * saves both access and refresh tokens encrypted to sharedPreference
     * @param access - Access Token
     * @param refresh - Refresh Token
     */
    public void saveTokens(Context context,String access,String refresh){
        saveAccessToken(context,access);
        saveRefreshToken(context,refresh);
    }

    /**
     * saves access token encrypted to sharedPreference
     * @param access
     */
    public void saveAccessToken(Context context,String access){

    }

    /**
     * saves refresh token encrypted to sharedPreference
     * @param refresh
     */
    public void saveRefreshToken(Context context,String refresh){

    }

    public String getToken(Context context,String key){
        return "";
    }


}
