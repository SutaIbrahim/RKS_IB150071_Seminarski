package com.example.ibrahim.servisinfo_ib150071.Helper;

import android.app.Application;
import android.content.Context;

import java.lang.ref.WeakReference;

/**
 * Created by Ibrahim on 25.7.2018..
 */

public class MyApp extends Application {

    private static WeakReference<Context> context;

    public static Context getContext() {
        return context.get();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        context = new WeakReference<>(getApplicationContext());
    }


}