package com.adonisarifi.fybermobileofferapi.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by AArifi on 10/10/2016
 * Project:Fyber
 * Email "adonisarifi@gmail.com"
 */

public class Information implements Parcelable {

    public String appName;
    public int appid;
    public String virtualCurrency;
    public String country;
    public String language;
    public String supportUrl;

    public Information() {

    }

    protected Information(Parcel in) {
        appName = in.readString();
        appid = in.readInt();
        virtualCurrency = in.readString();
        country = in.readString();
        language = in.readString();
        supportUrl = in.readString();
    }

    public static final Creator<Information> CREATOR = new Creator<Information>() {
        @Override
        public Information createFromParcel(Parcel in) {
            return new Information(in);
        }

        @Override
        public Information[] newArray(int size) {
            return new Information[size];
        }
    };

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public int getAppid() {
        return appid;
    }

    public void setAppid(int appid) {
        this.appid = appid;
    }

    public String getVirtualCurrency() {
        return virtualCurrency;
    }

    public void setVirtualCurrency(String virtualCurrency) {
        this.virtualCurrency = virtualCurrency;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getSupportUrl() {
        return supportUrl;
    }

    public void setSupportUrl(String supportUrl) {
        this.supportUrl = supportUrl;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(appName);
        parcel.writeInt(appid);
        parcel.writeString(virtualCurrency);
        parcel.writeString(country);
        parcel.writeString(language);
        parcel.writeString(supportUrl);
    }
}