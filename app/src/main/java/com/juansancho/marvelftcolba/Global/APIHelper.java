package com.juansancho.marvelftcolba.Global;

import android.content.Context;

import com.juansancho.marvelftcolba.R;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class APIHelper {

    public static final String BASE = "https://gateway.marvel.com";
    public static final String GET_COMICS = "/v1/public/characters/1009220/comics";
    public static final String GET_COMIC = "/v1/public/comics/";

    private static Context context;

    public APIHelper(Context context){
        this.context = context;
    }

    /**
     * @return ?ts={ts}&apikey={apikey}&hash={hash}
     */
    public static String authParams(int limit, int offset){
        String ts = ts();
        String s = "?ts=" + ts;
        s += "&apikey=" + context.getResources().getString(R.string.public_api_key);
        s += "&hash=" + md5(ts + context.getResources().getString(R.string.private_api_key) + context.getResources().getString(R.string.public_api_key));
        s += "&limit=" + limit;
        s += "&offset=" + offset;
        return s;
    }

    public static String authParams(){
        String ts = ts();
        String s = "?ts=" + ts;
        s += "&apikey=" + context.getResources().getString(R.string.public_api_key);
        s += "&hash=" + md5(ts + context.getResources().getString(R.string.private_api_key) + context.getResources().getString(R.string.public_api_key));
        return s;
    }

    /**
     *
     * @param s
     * @return the md5 of s
     */
    public static String md5(final String s) {
        final String MD5 = "MD5";
        try {
            // Create MD5 Hash
            MessageDigest digest = java.security.MessageDigest
                    .getInstance(MD5);
            digest.update(s.getBytes());
            byte messageDigest[] = digest.digest();

            // Create Hex String
            StringBuilder hexString = new StringBuilder();
            for (byte aMessageDigest : messageDigest) {
                String h = Integer.toHexString(0xFF & aMessageDigest);
                while (h.length() < 2)
                    h = "0" + h;
                hexString.append(h);
            }
            return hexString.toString();

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     *
     * @return current timestamp
     */
    public static String ts(){
        Long tsLong = System.currentTimeMillis()/1000;
        String ts = tsLong.toString();
        return ts;
    }
}
