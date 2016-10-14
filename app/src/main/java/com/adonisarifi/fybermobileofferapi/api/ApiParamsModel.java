package com.adonisarifi.fybermobileofferapi.api;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by AArifi on 10/11/2016
 * Project:Fyber
 * Email "adonisarifi@gmail.com"
 */

public class ApiParamsModel implements Parcelable {

    public ApiParamsModel(String params_name, String params_value) {
        this.params_name = params_name;
        this.params_value = params_value;
    }

    private String params_name;

    private String params_value;


    protected ApiParamsModel(Parcel in) {
        params_name = in.readString();
        params_value = in.readString();
    }

    public static final Creator<ApiParamsModel> CREATOR = new Creator<ApiParamsModel>() {
        @Override
        public ApiParamsModel createFromParcel(Parcel in) {
            return new ApiParamsModel(in);
        }

        @Override
        public ApiParamsModel[] newArray(int size) {
            return new ApiParamsModel[size];
        }
    };

    public String getParams_value() {
        return params_value;
    }

    public void setParams_value(String params_value) {
        this.params_value = params_value;
    }

    public String getParams_name() {
        return params_name;
    }

    public void setParams_name(String params_name) {
        this.params_name = params_name;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(params_name);
        parcel.writeString(params_value);
    }
}
