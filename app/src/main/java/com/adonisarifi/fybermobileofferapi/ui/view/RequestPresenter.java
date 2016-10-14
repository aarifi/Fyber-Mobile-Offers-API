package com.adonisarifi.fybermobileofferapi.ui.view;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.Log;

import com.adonisarifi.fybermobileofferapi.api.APIError;
import com.adonisarifi.fybermobileofferapi.api.ApiClient;
import com.adonisarifi.fybermobileofferapi.api.ApiErrorUtils;
import com.adonisarifi.fybermobileofferapi.api.ApiParamsModel;
import com.adonisarifi.fybermobileofferapi.model.ResultResponsOffer;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by AArifi on 10/10/2016
 * Project:Fyber
 * Email "adonisarifi@gmail.com"
 */

public class RequestPresenter implements RequestContract.Presenter {

    @NonNull
    private CompositeSubscription mSubscriptions;
    private final Context context;
    private final RequestContract.View requestContractView;
    private ApiClient apiClient;


    public RequestPresenter(Context context, RequestContract.View requestContractView) {
        this.context = context;
        this.requestContractView = requestContractView;
        mSubscriptions = new CompositeSubscription();
    }


    @Override
    public void subscribe() {

    }

    @Override
    public void unsubscribe() {
        mSubscriptions.clear();
    }

    @Override
    public ResultResponsOffer getResultResponsOfferObservable(ArrayList<ApiParamsModel> apiParamsModel) {
        requestContractView.setLoadingIndicator(true);
        final ResultResponsOffer[] resultResponsOffer = new ResultResponsOffer[1];
        Call<ResultResponsOffer> call = apiClient.getApiClientInstance(context, apiParamsModel).getApiService().getOfferFromFyber();

        call.enqueue(new Callback<ResultResponsOffer>() {
                         @Override
                         public void onResponse(Call<ResultResponsOffer> call, Response<ResultResponsOffer> response) {
                             if (response.isSuccessful()) {
                                 ResultResponsOffer offer = response.body();
                                 resultResponsOffer[0] = response.body();
                                 if (offer != null) {
                                     requestContractView.openMainActivity(offer);
                                 }
                             } else {
                                 // parse the response body …
                                 APIError error = ApiErrorUtils.parseError(response);
                                 requestContractView.showError(error.message());

                                 // … or just log the issue like we’re doing :)
                                 Log.d("error message", error.message());
                             }

                             requestContractView.setLoadingIndicator(false);


                         }

                         @Override
                         public void onFailure(Call<ResultResponsOffer> call, Throwable t) {
                             requestContractView.showError(t.getMessage());
                         }
                     }

        );

        return resultResponsOffer[0];
    }
}
