package com.freelance.rapaka.tvongo.model.services;


import com.freelance.rapaka.tvongo.model.beans.RefreshToken;
import com.freelance.rapaka.tvongo.model.beans.Tokens;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import rx.Observable;

/**
 * Created by jsi7ap2 on 22/08/2016.
 */
public interface RefreshTokenSynchronousCall {

    @POST("token/refresh")
    // @Headers({"Content-Type: application/json", "Authorization: Bearer eyJhbGciOiJSUzI1NiJ9.eyJpc3MiOiJDb2xydXl0LmVjdXN0b21lcm13IiwiaWF0IjoxNDcwNzQyMzM0LCJuYmYiOjE0NzA3NDIyMTQsImV4cCI6MTQ3MDc1NjczNCwic3ViIjoiQUNqYW5pMTQzQGNvbHJ1eXRncm91cC5jb20iLCJhdWQiOlsiaXppYXBwIiwiZWN1c3RvbWVybXciXSwiY29sciI6IjU0MDQyMDcifQ.nesS_LL_vW4h-W62qCcIysKBnyq6Q76CQoVrc9bLG7RozJ_hay8SJVz5syDn8xHmO42t5BXS_pr5d7A23j5zVaV0B-0iX8JL8O11NdGYAZjVDwJIOWxCX7UcptzdAgdNwz8H8_NBJhzn5yc8xZ_gZg6nBHAZoqHjlgUKF3zjBLPKVh7mjGK2IgX6ojUKEmsPEdb6WFWFZhX2jv__qGXvF4qj_24KHXO55M7KspMmpFM8b7SSaWRC8pICaC-_DnDNWMW3W_PgzzX0blShncNwdFt7r6PVES74C6VvrxQUvkZ0vRYQEOLwUG7S0UpLnvod_BtXMedFZZOGou5RHhBRRA"})
    Call<Tokens> getPostList(@Body RefreshToken refreshToken);
}
