package com.adonisarifi.fybermobileofferapi.ui.view;

import com.adonisarifi.fybermobileofferapi.util.BasePresenter;
import com.adonisarifi.fybermobileofferapi.util.BaseView;
import com.adonisarifi.fybermobileofferapi.model.Offers;
import com.adonisarifi.fybermobileofferapi.model.ResultResponsOffer;

/**
 * Created by AArifi on 10/10/2016
 * Project:Fyber
 * Email "adonisarifi@gmail.com"
 */

public interface OfferContract {

    interface View extends BaseView<Presenter> {

        void setupView();

        void setLoadingIndicator(boolean active);

        void showOffer(Offers offersList);

        void showLoadingOffersError();

        void showNoOffers();

        void setupRecyclerView();

        void setupDrawerLayout();

        void showOfferDetails(Offers offers);

        void openActivityChangeApiData();

    }

    interface Presenter extends BasePresenter {

        void loadOffer(boolean forceUpdate);

        void getOffer(ResultResponsOffer resultResponsOffer);

        void clearOffer();
    }
}
