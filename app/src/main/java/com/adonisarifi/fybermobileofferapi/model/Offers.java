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

public class Offers implements Parcelable {

    public String title;
    public int offerId;
    public String teaser;
    public String required_actions;
    public String link;
    public List<OfferType> offer_types = new ArrayList<OfferType>();
    public Thumbnail thumbnail;
    public int payout;
    public TimeToPayout time_to_payout;

    public Offers() {

    }

    protected Offers(Parcel in) {
        title = in.readString();
        offerId = in.readInt();
        teaser = in.readString();
        required_actions = in.readString();
        link = in.readString();
        offer_types = in.createTypedArrayList(OfferType.CREATOR);
        thumbnail = in.readParcelable(Thumbnail.class.getClassLoader());
        payout = in.readInt();
        time_to_payout = in.readParcelable(TimeToPayout.class.getClassLoader());
    }

    public static final Creator<Offers> CREATOR = new Creator<Offers>() {
        @Override
        public Offers createFromParcel(Parcel in) {
            return new Offers(in);
        }

        @Override
        public Offers[] newArray(int size) {
            return new Offers[size];
        }
    };

    public TimeToPayout getTime_to_payout() {
        return time_to_payout;
    }

    public void setTime_to_payout(TimeToPayout time_to_payout) {
        this.time_to_payout = time_to_payout;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getOfferId() {
        return offerId;
    }

    public void setOfferId(int offerId) {
        this.offerId = offerId;
    }

    public String getTeaser() {
        return teaser;
    }

    public void setTeaser(String teaser) {
        this.teaser = teaser;
    }

    public String getRequired_actions() {
        return required_actions;
    }

    public void setRequired_actions(String required_actions) {
        this.required_actions = required_actions;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public List<OfferType> getOffer_types() {
        return offer_types;
    }

    public void setOffer_types(List<OfferType> offer_types) {
        this.offer_types = offer_types;
    }

    public Thumbnail getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(Thumbnail thumbnail) {
        this.thumbnail = thumbnail;
    }

    public int getPayout() {
        return payout;
    }

    public void setPayout(int payout) {
        this.payout = payout;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(title);
        parcel.writeInt(offerId);
        parcel.writeString(teaser);
        parcel.writeString(required_actions);
        parcel.writeString(link);
        parcel.writeTypedList(offer_types);
        parcel.writeParcelable(thumbnail, i);
        parcel.writeInt(payout);
        parcel.writeParcelable(time_to_payout, i);
    }
}