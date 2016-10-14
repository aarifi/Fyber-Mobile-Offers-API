package com.adonisarifi.fybermobileofferapi.api;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by AArifi on 10/12/2016
 * Project:Fyber
 * Email "adonisarifi@gmail.com"
 */

public class APIError implements Parcelable{

    private int statusCode;
    private String message;

    public APIError(int statusCode, String message) {
        this.statusCode = statusCode;
        this.message = message;
    }

    public APIError() {
    }

    protected APIError(Parcel in) {
        statusCode = in.readInt();
        message = in.readString();
    }

    public static final Creator<APIError> CREATOR = new Creator<APIError>() {
        @Override
        public APIError createFromParcel(Parcel in) {
            return new APIError(in);
        }

        @Override
        public APIError[] newArray(int size) {
            return new APIError[size];
        }
    };

    public int status() {
        return statusCode;
    }

    public String message() {
        return message;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(statusCode);
        parcel.writeString(message);
    }
}
