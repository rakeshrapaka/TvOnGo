package com.freelance.rapaka.tvongo.model.module;

import android.content.Context;
import android.util.Log;

import com.freelance.rapaka.tvongo.TvOnGo;
import com.freelance.rapaka.tvongo.helper.TokenHelper;
import com.freelance.rapaka.tvongo.model.beans.Client;
import com.freelance.rapaka.tvongo.model.beans.RefreshToken;
import com.freelance.rapaka.tvongo.model.beans.Tokens;
import com.freelance.rapaka.tvongo.model.services.RefreshTokenSynchronousCall;
import com.freelance.rapaka.tvongo.model.services.ServiceGenerator;
import com.freelance.rapaka.tvongo.util.Constants;

import java.io.IOException;


import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Call;

public class HttpInterceptor implements Interceptor {

	private Context hContext;

	private OkHttpClient.Builder hclient;

	private String refreshUrl = "";

	public HttpInterceptor(Context context,OkHttpClient.Builder client,String refreshUrl ){
		hContext = context;
		hclient = client;
		this.refreshUrl = refreshUrl;
	}

	@Override
	public Response intercept(Chain chain) throws IOException {
		String token = TokenHelper.getInstance().getToken(hContext,"");
		Request request = chain.request();

		//Build new request
		Request.Builder builder = request.newBuilder();
		builder.header("Content-Type", "application/json");
		setAuthHeader(builder, token);

		request = builder.build(); //overwrite old request
		Response response = chain.proceed(request); //perform request, here original request will be executed

		if (response.code() == 401) { //if unauthorized
			synchronized (hclient) {

				String currentToken = TokenHelper.getInstance().getToken(hContext,"");

				if (currentToken != null && currentToken.equals(token)) {

					int code = refreshToken() / 100;
					if (code != 2) {
						if (code == 4) {

						}
						return response;
					}
				}
				String newtoken = TokenHelper.getInstance().getToken(hContext,"");
				if (newtoken != null) { //retry requires new auth token,
					setAuthHeader(builder, newtoken); //set auth token to updated
					request = builder.build();
					return chain.proceed(request); //repeat request with new token
				}
			}
		}


		return response;
	}
	
	private void setAuthHeader(Request.Builder builder, String token) {
		if (token != null) //Add Auth token to each request if authorized
			builder.header("Authorization", String.format("Bearer %s", token));
	}

	private int refreshToken(){
		int responseCode = 0;
		Client client = new Client("tvongo");
		String refreshtoken = TokenHelper.getInstance().getToken(hContext,"");
		RefreshTokenSynchronousCall tokenService = ServiceGenerator.createService(RefreshTokenSynchronousCall.class, refreshUrl);
		Call<Tokens> call = tokenService.getPostList(new RefreshToken(client,refreshtoken));
		try {
			retrofit2.Response callResponse = call.execute();
			responseCode = callResponse.code();
			Tokens tokens = (Tokens) callResponse.body();

			if(tokens.getAccessToken()!=null && tokens.getAccessToken().length()>0 && tokens.getRefreshToken()!=null && tokens.getRefreshToken().length()>0) {
				TokenHelper.getInstance().saveTokens(hContext, tokens.getAccessToken(), tokens.getRefreshToken());
			}else{
				Log.e(Constants.TAG,"wrong token");
			}

		}catch (Exception e){
			if(e!=null)
				Log.e(Constants.TAG,e.getMessage());
		}

		return responseCode;
	}

}