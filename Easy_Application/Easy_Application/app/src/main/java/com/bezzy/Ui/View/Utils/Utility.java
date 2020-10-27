package com.bezzy.Ui.View.Utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;

public class Utility {
    public static boolean internet_check(Context context){
        //Test for Connection
        ConnectivityManager cmgr=(ConnectivityManager) context.getApplicationContext().getSystemService(Context.CONNECTIVITY_SERVICE);
        if (cmgr.getActiveNetworkInfo()!= null && cmgr.getActiveNetworkInfo().isAvailable() && cmgr.getActiveNetworkInfo().isConnected()) {
            return true;
        }
        else
        {
            return false;
        }
    }

    public static void setLogin(Context mContext, String type) {
        SharedPreferences preferences = mContext.getSharedPreferences("Bezzy", 0);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("login", type);
        editor.apply();
    }

    public static String getLogin(Context mContext) {
        SharedPreferences memIdPreferences = mContext.getSharedPreferences("Bezzy", 0); // 0 - for private mode
        return memIdPreferences.getString("login", "");
    }

    public static void setOtpScreen(Context mContext, String type) {
        SharedPreferences preferences = mContext.getSharedPreferences("Bezzy", 0);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("otpscreen", type);
        editor.apply();
    }

    public static String getOtpScreen(Context mContext) {
        SharedPreferences memIdPreferences = mContext.getSharedPreferences("Bezzy", 0); // 0 - for private mode
        return memIdPreferences.getString("otpscreen", "");
    }

    public static void setUserId(Context mContext, String type) {
        SharedPreferences preferences = mContext.getSharedPreferences("Bezzy", 0);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("userId", type);
        editor.apply();
    }

    public static String getUserId(Context mContext) {
        SharedPreferences memIdPreferences = mContext.getSharedPreferences("Bezzy", 0); // 0 - for private mode
        return memIdPreferences.getString("userId", "");
    }

    public static void setUserName(Context mContext, String type) {
        SharedPreferences preferences = mContext.getSharedPreferences("Bezzy", 0);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("userName", type);
        editor.apply();
    }

    public static String getUserName(Context mContext) {
        SharedPreferences memIdPreferences = mContext.getSharedPreferences("Bezzy", 0); // 0 - for private mode
        return memIdPreferences.getString("userName", "");
    }
    public static void setName(Context mContext, String type) {
        SharedPreferences preferences = mContext.getSharedPreferences("Bezzy", 0);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("Name", type);
        editor.apply();
    }

    public static String getName(Context mContext) {
        SharedPreferences memIdPreferences = mContext.getSharedPreferences("Bezzy", 0); // 0 - for private mode
        return memIdPreferences.getString("Name", "");
    }
    public static void setEmail(Context mContext, String type) {
        SharedPreferences preferences = mContext.getSharedPreferences("Bezzy", 0);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("Email", type);
        editor.apply();
    }

    public static String getEmail(Context mContext) {
        SharedPreferences memIdPreferences = mContext.getSharedPreferences("Bezzy", 0); // 0 - for private mode
        return memIdPreferences.getString("Email", "");
    }
    public static void setdob(Context mContext, String type) {
        SharedPreferences preferences = mContext.getSharedPreferences("Bezzy", 0);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("Dob", type);
        editor.apply();
    }

    public static String getdob(Context mContext) {
        SharedPreferences memIdPreferences = mContext.getSharedPreferences("Bezzy", 0); // 0 - for private mode
        return memIdPreferences.getString("Dob", "");
    }
    public static void setGender(Context mContext, String type) {
        SharedPreferences preferences = mContext.getSharedPreferences("Bezzy", 0);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("Gender", type);
        editor.apply();
    }

    public static String getGender(Context mContext) {
        SharedPreferences memIdPreferences = mContext.getSharedPreferences("Bezzy", 0); // 0 - for private mode
        return memIdPreferences.getString("Gender", "");
    }



}
