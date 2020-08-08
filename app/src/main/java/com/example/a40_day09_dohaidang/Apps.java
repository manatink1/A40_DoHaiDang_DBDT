package com.example.a40_day09_dohaidang;

import android.app.Application;

import com.facebook.stetho.Stetho;

public class Apps extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        Stetho.initializeWithDefaults(this);
    }
}
