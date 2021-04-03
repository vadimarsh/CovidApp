package com.arsh.covidrf;

import android.app.Application;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

public class App extends Application {
    public static final String TAG = "DEFAULT";
    private static RequestQueue requestQueue;
    private static App app;

    @Override
    public void onCreate() {
        super.onCreate();
        app=this;
    }
    public static synchronized App getApp(){
        return app;
    }

    private RequestQueue getRequestQueue(){
        if(requestQueue == null){
            requestQueue = Volley.newRequestQueue(this.getApplicationContext());
        }
        return requestQueue;
    }

    public void addToRequestQueue(Request request) {
        request.setTag(TAG);
        request.setRetryPolicy(new DefaultRetryPolicy(5000, 5,DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
        getRequestQueue().add(request);
    }
    public void addToRequestQueue(Request request, Object tag) {
        request.setTag(tag);
        getRequestQueue().add(request);
    }
    public void cancelPendingRequest(Object tag){
        getRequestQueue().cancelAll(tag);
    }
}
