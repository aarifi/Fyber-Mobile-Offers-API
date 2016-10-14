package com.adonisarifi.fybermobileofferapi.util;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.provider.Settings;

import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by AArifi on 10/11/2016
 * Project:Fyber
 * Email "adonisarifi@gmail.com"
 */

public class SupportMethod {

    public final Context mContext;
    public static SupportMethod supportMethod;

    public static SupportMethod getGeSupportMethodInstance(Context context) {
        if (supportMethod == null) {
            supportMethod = new SupportMethod(context);
        }
        return supportMethod;
    }

    public SupportMethod(Context mContext) {
        this.mContext = mContext;
    }

    public static String getDeviceId(Context context) {
        String android_id = Settings.Secure.getString(context.getContentResolver(),
                Settings.Secure.ANDROID_ID);
       // Log.d("DeviceId", android_id.toString());
        return android_id;
    }

    public static String getCurrentTimeStampOnUTC() {
        String dateTimeUtc = null;
        try {
            // get current moment in default time zone
            DateTime dt = new DateTime();

            // translate to UTC local time
            DateTime dateTimeWithZoneUtc = dt.withZone(DateTimeZone.UTC);

            DateTimeFormatter dateTimeFormatter = DateTimeFormat.forPattern("yyyyMMddHHmmss");

            dateTimeUtc = dateTimeWithZoneUtc.toString(dateTimeFormatter);
        } catch (Exception e) {
            e.getMessage();
        }

        return dateTimeUtc;
    }

    public static String getCurrentTimeStamp() {
        try {

            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmss");
            String currentTimeStamp = dateFormat.format(new Date()); // Find todays date

            return currentTimeStamp;
        } catch (Exception e) {
            e.printStackTrace();

            return null;
        }
    }

    public static Intent openUrl(String url) {

        Intent intent = null;
        Uri uri = null;
        String stringUrl = url;

        if (stringUrl != null && stringUrl.toCharArray().length > 0 && stringUrl.contains("http")) {
            uri = Uri.parse(stringUrl); // missing 'http://' will cause crashed
            intent = new Intent(Intent.ACTION_VIEW, uri);
            //Calling startActivity() from outside of an Activity
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        }

        return intent;
    }

    public boolean isNetworkConnectivity() {
        ConnectivityManager connectivityManager = (ConnectivityManager) mContext.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        return networkInfo != null && networkInfo.isConnectedOrConnecting();
    }

}
