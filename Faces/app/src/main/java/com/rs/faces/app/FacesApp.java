package com.rs.faces.app;

import android.app.Application;

import com.facebook.stetho.Stetho;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class FacesApp extends Application {

    private static FacesApp appInstance;

    private GsonBuilder gsonBuilder;
    private Gson gsonInstance;

    @Override
    public void onCreate() {
        super.onCreate();

        appInstance = this;

        initialiseLibraries();
    }

    private void initialiseLibraries() {

        // Allow Debugging network calls
        Stetho.initializeWithDefaults(this);
    }

    public static FacesApp getAppInstance() {
        return appInstance;
    }

    public GsonBuilder getGsonBuilder() {
        if (gsonBuilder == null) {
            gsonBuilder = new GsonBuilder();
        }
        return gsonBuilder;
    }

    public Gson getGsonInstance() {
        if (gsonInstance == null) {
            gsonInstance = getGsonBuilder().create();
        }
        return gsonInstance;
    }
}
