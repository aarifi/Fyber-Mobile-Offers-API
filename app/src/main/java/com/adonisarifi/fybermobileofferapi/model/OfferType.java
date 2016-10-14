package com.adonisarifi.fybermobileofferapi.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by AArifi on 10/10/2016
 * Project:Fyber
 * Email "adonisarifi@gmail.com"
 */

public class OfferType implements Parcelable {

    public int offerTypeId;
    public String readable;

    public OfferType() {

    }

    protected OfferType(Parcel in) {
        offerTypeId = in.readInt();
        readable = in.readString();
    }

    public static final Creator<OfferType> CREATOR = new Creator<OfferType>() {
        @Override
        public OfferType createFromParcel(Parcel in) {
            return new OfferType(in);
        }

        @Override
        public OfferType[] newArray(int size) {
            return new OfferType[size];
        }
    };

    public int getOfferTypeId() {
        return offerTypeId;
    }

    public void setOfferTypeId(int offerTypeId) {
        this.offerTypeId = offerTypeId;
    }

    public String getReadable() {
        return readable;
    }

    public void setReadable(String readable) {
        this.readable = readable;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(offerTypeId);
        parcel.writeString(readable);
    }
}
