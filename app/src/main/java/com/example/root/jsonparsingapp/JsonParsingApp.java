package com.example.root.jsonparsingapp;

import android.app.Application;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class JsonParsingApp extends Application {

    private static JsonParsingApp mInstance;
    private RequestQueue mRequestQueue;


    @Override
    public void onCreate() {
        super.onCreate();
        mInstance=this;
        mRequestQueue= Volley.newRequestQueue(this);
    }

    public static  JsonParsingApp getmInstance()
    {
        return mInstance;
    }

    public RequestQueue getmRequestQueue() {
        return mRequestQueue;
    }

    @Override
    public void onTerminate() {
        super.onTerminate();
    }
}
