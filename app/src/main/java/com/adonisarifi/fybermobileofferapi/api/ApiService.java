package com.adonisarifi.fybermobileofferapi.api;

import com.adonisarifi.fybermobileofferapi.model.ResultResponsOffer;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;

/**
 * Created by AArifi on 10/10/2016
 * Project:Fyber
 * Email "adonisarifi@gmail.com"
 */

public interface ApiService {
    @Headers({"Cache-Control: max-age=640000"})
    @GET("/feed/v1/offers.json/")
    Call<ResultResponsOffer> getOfferFromFyber();

}
