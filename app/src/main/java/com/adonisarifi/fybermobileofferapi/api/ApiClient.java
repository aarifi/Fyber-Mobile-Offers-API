package com.adonisarifi.fybermobileofferapi.api;

import android.content.Context;
import android.util.Log;

import com.adonisarifi.fybermobileofferapi.util.Constants;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by AArifi on 10/10/2016
 * Project:Fyber
 * Email "adonisarifi@gmail.com"
 */

public class ApiClient implements Interceptor {

    private static final String TAG = ApiClient.class.getSimpleName();
    public static ApiClient apiClientInstance;
    private ApiService apiService;
    private Context context;
    private ArrayList<ApiParamsModel> apiParamsModel;
    public static Retrofit retrofit;

    public ApiClient(Context context, ArrayList<ApiParamsModel> paramsModel) {
        this.context = context;
        this.apiParamsModel = paramsModel;
    }

    public static ApiClient getApiClientInstance(Context context, ArrayList<ApiParamsModel> paramsModel) {

        apiClientInstance = new ApiClient(context, paramsModel);
        return apiClientInstance;
    }

    public ApiService getApiService() {

        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
                .create();

        // Add the interceptor to OkHttpClient
        OkHttpClient.Builder okhttpClient = new OkHttpClient.Builder();
        okhttpClient.addInterceptor(this);
        okhttpClient.connectTimeout(30, TimeUnit.SECONDS);
        okhttpClient.readTimeout(60, TimeUnit.SECONDS);
        OkHttpClient client = okhttpClient.build();


     retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .client(client)
                .build();
        apiService = retrofit.create(ApiService.class);
        return apiService;
    }

    @Override
    public Response intercept(Chain chain) throws IOException {
        Request original = chain.request();
        HttpUrl originalHttpUrl = original.url();

        HttpUrl url = originalHttpUrl.newBuilder()
                .addQueryParameter(apiParamsModel.get(0).getParams_name(), apiParamsModel.get(0).getParams_value())
                .addQueryParameter(apiParamsModel.get(1).getParams_name(), apiParamsModel.get(1).getParams_value())
                .addQueryParameter(apiParamsModel.get(2).getParams_name(), apiParamsModel.get(2).getParams_value())
                .addQueryParameter(apiParamsModel.get(3).getParams_name(), apiParamsModel.get(3).getParams_value())
                .addQueryParameter(apiParamsModel.get(4).getParams_name(), apiParamsModel.get(4).getParams_value())
                .addQueryParameter(apiParamsModel.get(5).getParams_name(), apiParamsModel.get(5).getParams_value())
                .addQueryParameter(apiParamsModel.get(6).getParams_name(), apiParamsModel.get(6).getParams_value())
                .addQueryParameter(apiParamsModel.get(7).getParams_name(), apiParamsModel.get(7).getParams_value())
                .build();
        // Request customization: add request headers
        Request.Builder requestBuilder = original.newBuilder()
                .url(url);

        Request request = requestBuilder.build();
        Log.d(TAG, "intercept: " + request.toString());
        return chain.proceed(request);
    }

}
