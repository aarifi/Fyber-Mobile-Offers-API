package com.adonisarifi.fybermobileofferapi.util;

import android.util.Log;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Created by AArifi on 10/11/2016
 * Project:Fyber
 * Email "adonisarifi@gmail.com"
 */

public class TextToSHA1 {

    public static String getsha1(String stringForConverter) throws NoSuchAlgorithmException {
        MessageDigest mDigest = MessageDigest.getInstance("SHA1");
        String completString = stringForConverter;
        byte[] result = mDigest.digest(completString.getBytes());
        StringBuffer strinBufferSha1 = new StringBuffer();
        for (int i = 0; i < result.length; i++) {
            strinBufferSha1.append(Integer.toString((result[i] & 0xff) + 0x100, 16).substring(1));

        }
        Log.d("TextToShA1", completString);

        return strinBufferSha1.toString();
    }
}
