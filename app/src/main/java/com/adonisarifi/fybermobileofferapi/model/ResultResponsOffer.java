package com.adonisarifi.fybermobileofferapi.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by AArifi on 10/10/2016
 * Project:Fyber
 * Email "adonisarifi@gmail.com"
 */

public class ResultResponsOffer implements Parcelable {

    public String code;
    public String message;
    public int count;
    public int pages;
    public Information information;
    public List<Offers> offers = new ArrayList<Offers>();

    public ResultResponsOffer() {

    }

    protected ResultResponsOffer(Parcel in) {
        code = in.readString();
        message = in.readString();
        count = in.readInt();
        pages = in.readInt();
        information = in.readParcelable(Information.class.getClassLoader());
        offers = in.createTypedArrayList(Offers.CREATOR);
    }

    public static final Creator<ResultResponsOffer> CREATOR = new Creator<ResultResponsOffer>() {
        @Override
        public ResultResponsOffer createFromParcel(Parcel in) {
            return new ResultResponsOffer(in);
        }

        @Override
        public ResultResponsOffer[] newArray(int size) {
            return new ResultResponsOffer[size];
        }
    };

    public List<Offers> getOffers() {
        return offers;
    }

    public void setOffers(List<Offers> offers) {
        this.offers = offers;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getPages() {
        return pages;
    }

    public void setPages(int pages) {
        this.pages = pages;
    }

    public Information getInformation() {
        return information;
    }

    public void setInformation(Information information) {
        this.information = information;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(code);
        parcel.writeString(message);
        parcel.writeInt(count);
        parcel.writeInt(pages);
        parcel.writeParcelable(information, i);
        parcel.writeTypedList(offers);
    }
}
