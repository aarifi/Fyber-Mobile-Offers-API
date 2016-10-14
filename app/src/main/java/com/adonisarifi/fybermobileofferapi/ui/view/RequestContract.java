package com.adonisarifi.fybermobileofferapi.ui.view;

import com.adonisarifi.fybermobileofferapi.util.BasePresenter;
import com.adonisarifi.fybermobileofferapi.util.BaseView;
import com.adonisarifi.fybermobileofferapi.api.ApiParamsModel;
import com.adonisarifi.fybermobileofferapi.model.ResultResponsOffer;

import java.util.ArrayList;

/**
 * Created by AArifi on 10/10/2016
 * Project:Fyber
 * Email "adonisarifi@gmail.com"
 */

public interface RequestContract {

    interface View extends BaseView<Presenter> {

        void initControls();

        void startRequestForData();

        String getSha1(String fullUrl);

        void setLoadingIndicator(boolean active);

        void showError(String message);

        boolean validateInputeField();

        ArrayList<ApiParamsModel> getApiParamsModel();

        void openMainActivity(ResultResponsOffer resultResponsOffer);
    }

    interface Presenter extends BasePresenter {

        ResultResponsOffer getResultResponsOfferObservable(ArrayList<ApiParamsModel> apiParamsModel);
    }
}
