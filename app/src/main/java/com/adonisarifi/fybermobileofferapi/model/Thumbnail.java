package com.adonisarifi.fybermobileofferapi.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by AArifi on 10/10/2016
 * Project:Fyber
 * Email "adonisarifi@gmail.com"
 */

public class Thumbnail implements Parcelable {
    public String lowres;
    public String hires;

    public Thumbnail() {

    }

    protected Thumbnail(Parcel in) {
        lowres = in.readString();
        hires = in.readString();
    }

    public static final Creator<Thumbnail> CREATOR = new Creator<Thumbnail>() {
        @Override
        public Thumbnail createFromParcel(Parcel in) {
            return new Thumbnail(in);
        }

        @Override
        public Thumbnail[] newArray(int size) {
            return new Thumbnail[size];
        }
    };

    public String getLowres() {
        return lowres;
    }

    public void setLowres(String lowres) {
        this.lowres = lowres;
    }

    public String getHires() {
        return hires;
    }

    public void setHires(String hires) {
        this.hires = hires;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(lowres);
        parcel.writeString(hires);
    }
}
