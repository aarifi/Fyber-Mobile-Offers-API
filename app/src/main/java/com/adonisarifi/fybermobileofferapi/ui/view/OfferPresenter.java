package com.adonisarifi.fybermobileofferapi.ui.view;

import com.adonisarifi.fybermobileofferapi.model.Offers;
import com.adonisarifi.fybermobileofferapi.model.ResultResponsOffer;
import com.adonisarifi.fybermobileofferapi.ui.view.OfferContract.Presenter;

import java.util.List;

/**
 * Created by AArifi on 10/10/2016
 * Project:Fyber
 * Email "adonisarifi@gmail.com"
 */

public class OfferPresenter implements Presenter {

    private final OfferContract.View offerContractView;

    public OfferPresenter(OfferContract.View offerContractView) {
        this.offerContractView = offerContractView;
    }


    @Override
    public void loadOffer(boolean forceUpdate) {

    }

    @Override
    public void getOffer(ResultResponsOffer resultResponsOffer) {

        List<Offers> offersList = resultResponsOffer.getOffers();
        if (offersList != null) {
            for (Offers offer : offersList) {
                offerContractView.showOffer(offer);
            }
        }
    }

    @Override
    public void clearOffer() {

    }

    @Override
    public void subscribe() {

    }

    @Override
    public void unsubscribe() {

    }
}
