package com.adonisarifi.fybermobileofferapi.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by AArifi on 10/10/2016
 * Project:Fyber
 * Email "adonisarifi@gmail.com"
 */

public class TimeToPayout implements Parcelable {

    public TimeToPayout() {

    }

    public int amount;
    public String readable;

    protected TimeToPayout(Parcel in) {
        amount = in.readInt();
        readable = in.readString();
    }

    public static final Creator<TimeToPayout> CREATOR = new Creator<TimeToPayout>() {
        @Override
        public TimeToPayout createFromParcel(Parcel in) {
            return new TimeToPayout(in);
        }

        @Override
        public TimeToPayout[] newArray(int size) {
            return new TimeToPayout[size];
        }
    };

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
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
        parcel.writeInt(amount);
        parcel.writeString(readable);
    }
}
