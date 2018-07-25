package com.example.ibrahim.servisinfo_ib150071.Helper;

/**
 * Created by Ibrahim on 25.7.2018..
 */

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class MyGson {
    public static Gson build()
    {
        return builder().create();
    }
    public static GsonBuilder builder()
    {
        GsonBuilder builder = new GsonBuilder();

        builder.setDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        return builder;
    }
}